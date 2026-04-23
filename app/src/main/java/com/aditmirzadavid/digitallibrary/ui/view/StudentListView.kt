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
import com.aditmirzadavid.digitallibrary.ui.viewmodel.StudentListState
import com.aditmirzadavid.digitallibrary.ui.viewmodel.StudentListViewModel

@Composable
fun StudentListView(
    navController: NavHostController,
    viewModel: StudentListViewModel
) {

    // Mengambil data state dari ViewModel dalam bentuk StateFlow,
    // lalu mengubahnya menjadi Compose State agar dapat diamati oleh UI.
    val state by viewModel.state.collectAsState()

    Box(modifier = Modifier.fillMaxSize()) {

        // Setiap state (Loading, Success, Error) akan menghasilkan
        // tampilan UI yang berbeda.
        when (state) {

            is StudentListState.Loading -> {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center)
                )
            }

            is StudentListState.Error -> {
                Text(
                    text = (state as StudentListState.Error).message,
                    modifier = Modifier.align(Alignment.Center)
                )
            }

            is StudentListState.Success -> {

                val students = (state as StudentListState.Success).data

                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(8.dp)
                ) {
                    // Fungsi items digunakan untuk melakukan iterasi terhadap list 'students'
                    // dan menampilkan setiap item dalam bentuk UI (StudentCard).
                    items(students){ students ->
                        StudentCard(student = students)
                    }
                }
            }
        }

        // FAB untuk tambah student
        FloatingActionButton(
            onClick = {
                navController.navigate(AppView.AddStudent.name)
            },
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp)
        ) {
            Icon(Icons.Default.Add, contentDescription = "Add Student")
        }
    }
}