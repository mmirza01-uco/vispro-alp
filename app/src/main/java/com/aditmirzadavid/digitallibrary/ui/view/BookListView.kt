package com.aditmirzadavid.digitallibrary.ui.view

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.aditmirzadavid.digitallibrary.ui.route.AppView
import com.aditmirzadavid.digitallibrary.ui.viewmodel.BookListState
import com.aditmirzadavid.digitallibrary.ui.viewmodel.BookListViewModel
@Composable
fun BookListView(
    navController: NavHostController,
    viewModel: BookListViewModel
) {

    // Mengambil data state dari ViewModel dalam bentuk StateFlow,
    // lalu mengubahnya menjadi Compose State agar dapat diamati oleh UI.
    val state by viewModel.state.collectAsState()

    Box(modifier = Modifier.fillMaxSize()) {

        // Setiap state (Loading, Success, Error) akan menghasilkan
        // tampilan UI yang berbeda.
        when (state) {

            is BookListState.Loading -> {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center)
                )
            }

            is BookListState.Error -> {
                Text(
                    text = (state as BookListState.Error).message,
                    modifier = Modifier.align(Alignment.Center)
                )
            }

            is BookListState.Success -> {

                val books = (state as BookListState.Success).data

                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(8.dp)
                ) {

                    // Fungsi items digunakan untuk melakukan iterasi terhadap list 'books'
                    // dan menampilkan setiap item dalam bentuk UI (BookCard).
                    items(books) { book ->
                        BookCard(
                            book = book,
                            onClick = {
                                navController.navigate("${AppView.BookDetail.name}/${book.idbuku}")
                            }
                        )
                    }
                }
            }
        }

        // Floating Action Button Buat Nambah Buku
        FloatingActionButton(
            onClick = {
                navController.navigate(AppView.AddBook.name)
            },
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp)
        ) {
            Icon(Icons.Default.Add, contentDescription = "Tambah Buku")
        }
    }
}