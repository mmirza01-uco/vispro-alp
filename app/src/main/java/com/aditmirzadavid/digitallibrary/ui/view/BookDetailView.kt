package com.aditmirzadavid.digitallibrary.ui.view

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.aditmirzadavid.digitallibrary.ui.viewmodel.BookDetailState
import com.aditmirzadavid.digitallibrary.ui.viewmodel.BookDetailViewModel

@Composable
fun BookDetailView(
    idbuku: String,
    viewModel: BookDetailViewModel
) {
    val state by viewModel.state.collectAsState()

    // trigger API saat pertama kali masuk
    LaunchedEffect(idbuku) {
        viewModel.getBookById(idbuku)
    }

    // Setiap state (Loading, Success, Error) akan menghasilkan
    // tampilan UI yang berbeda.
    when (state) {

        is BookDetailState.Loading -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }

        is BookDetailState.Error -> {
            val message = (state as BookDetailState.Error).message

            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(text = message)
            }
        }

        is BookDetailState.Success -> {
            val book = (state as BookDetailState.Success).data

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                // AsyncImage digunakan untuk menampilkan gambar dari URL (internet)
                // secara asynchronous menggunakan library Coil.
                val imageUrl = "http://192.168.1.9/elibrary/poster/${book.posterPath}"
                AsyncImage(
                    model = imageUrl,
                    contentDescription = book.judul,
                    modifier = Modifier.height(300.dp).fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = book.judul,
                    style = MaterialTheme.typography.titleLarge
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text("Penulis: ${book.pengarang}")
                Text("Penerbit: ${book.penerbit}")
                Text("Kategori: ${book.kategori}")
                Text("ISBN: ${book.isbn}")
                Text("Stok: ${book.stok}")

                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    text = "Deskripsi:",
                    style = MaterialTheme.typography.titleMedium
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(text = book.deskripsi)
            }
        }
    }
}