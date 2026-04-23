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
import com.aditmirzadavid.digitallibrary.data.dto.RequestLoan
import com.aditmirzadavid.digitallibrary.ui.viewmodel.AddLoanState
import com.aditmirzadavid.digitallibrary.ui.viewmodel.AddLoanViewModel
@Composable
fun AddLoanView(
    viewModel: AddLoanViewModel
) {

    var idbuku by remember { mutableStateOf("") }
    var nis by remember { mutableStateOf("") }
    var statusPinjam by remember { mutableStateOf("1") } // default 1

    val state by viewModel.state.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Text(
            text = "Tambah Peminjaman",
            style = MaterialTheme.typography.titleLarge
        )

        Spacer(modifier = Modifier.height(12.dp))

        // ID Buku
        OutlinedTextField(
            value = idbuku,
            onValueChange = { idbuku = it.uppercase() },
            label = { Text("ID Buku") },
            modifier = Modifier.fillMaxWidth()
        )

        // NIS
        OutlinedTextField(
            value = nis,
            onValueChange = { nis = it },
            label = { Text("NIS Siswa") },
            modifier = Modifier.fillMaxWidth()
        )

        // Status Pinjam
        OutlinedTextField(
            value = statusPinjam,
            onValueChange = { statusPinjam = it },
            label = { Text("Status Pinjam (1 = dipinjam)") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        //  BUTTON SIMPAN
        Button(
            onClick = {

                // VALIDASI
                if (idbuku.isBlank() || nis.isBlank()) return@Button

                val request = RequestLoan(
                    idbuku = idbuku,
                    nis = nis,
                    statusPinjam = statusPinjam.toIntOrNull() ?: 1
                )

                viewModel.addLoan(request)
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Simpan")
        }

        Spacer(modifier = Modifier.height(16.dp))

        // STATE HANDLING

        when (state) {

            is AddLoanState.Loading -> {
                CircularProgressIndicator()
            }

            is AddLoanState.Success -> {
                Text("Peminjaman berhasil ditambahkan ✅")

                // reset form
                idbuku = ""
                nis = ""
                statusPinjam = "1"
            }

            is AddLoanState.Error -> {
                Text((state as AddLoanState.Error).message)
            }

            else -> {}
        }
    }
}