package com.aditmirzadavid.digitallibrary.ui.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.aditmirzadavid.digitallibrary.data.dto.RequestBook
import com.aditmirzadavid.digitallibrary.ui.viewmodel.AddBookState
import com.aditmirzadavid.digitallibrary.ui.viewmodel.AddBookViewModel

@Composable
fun AddBookView(
    viewModel: AddBookViewModel
) {
    var idbuku by remember { mutableStateOf("") }
    var judul by remember { mutableStateOf("") }
    var pengarang by remember { mutableStateOf("") }
    var penerbit by remember { mutableStateOf("") }
    var kategori by remember { mutableStateOf("") }
    var isbn by remember { mutableStateOf("") }
    var deskripsi by remember { mutableStateOf("") }
    var stok by remember { mutableStateOf("") }

    val state by viewModel.state.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Text("Tambah Buku", style = MaterialTheme.typography.titleLarge)

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(
            value = idbuku,
            onValueChange = { idbuku = it },
            label = { Text("ID Buku") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = judul,
            onValueChange = { judul = it },
            label = { Text("Judul") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = pengarang,
            onValueChange = { pengarang = it },
            label = { Text("Pengarang") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = penerbit,
            onValueChange = { penerbit = it },
            label = { Text("Penerbit") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = kategori,
            onValueChange = { kategori = it },
            label = { Text("Kategori") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = isbn,
            onValueChange = { isbn = it },
            label = { Text("ISBN") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = stok,
            onValueChange = { stok = it },
            label = { Text("Stok") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = deskripsi,
            onValueChange = { deskripsi = it },
            label = { Text("Deskripsi") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {

                
                if (
                    idbuku.isBlank() ||
                    judul.isBlank() ||
                    pengarang.isBlank() ||
                    stok.isBlank()
                ) return@Button

                val request = RequestBook(
                    idbuku = idbuku,
                    deskripsi = deskripsi,
                    isbn = isbn,
                    judul = judul,
                    kategori = kategori,
                    penerbit = penerbit,
                    pengarang = pengarang,
                    posterPath = "$idbuku.png",
                    posterResId = 1,
                    stok = stok.toIntOrNull() ?: 0
                )
                // Mengirim data ke API
                viewModel.addBook(request)
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Simpan")
        }

        Spacer(modifier = Modifier.height(16.dp))

        // 🔥 STATE HANDLING

        when (state) {

            is AddBookState.Loading -> {
                CircularProgressIndicator()
            }

            is AddBookState.Success -> {
                Text("Berhasil menambahkan buku ✅")

                // reset form
                judul = ""
                pengarang = ""
                penerbit = ""
                kategori = ""
                isbn = ""
                deskripsi = ""
                stok = ""
            }

            is AddBookState.Error -> {
                Text((state as AddBookState.Error).message)
            }

            else -> {}
        }
    }
}