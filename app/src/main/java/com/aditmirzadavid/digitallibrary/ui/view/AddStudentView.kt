package com.aditmirzadavid.digitallibrary.ui.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.aditmirzadavid.digitallibrary.data.dto.RequestStudent
import com.aditmirzadavid.digitallibrary.ui.viewmodel.AddStudentState
import com.aditmirzadavid.digitallibrary.ui.viewmodel.AddStudentViewModel

@Composable
fun AddStudentView(
    viewModel: AddStudentViewModel
) {

    var nis by remember { mutableStateOf("") }
    var nama by remember { mutableStateOf("") }
    var kelas by remember { mutableStateOf("") }
    var angkatan by remember { mutableStateOf("") }
    var gender by remember { mutableStateOf("MALE") } // default
    var isCompleted by remember { mutableStateOf(true) }

    val state by viewModel.state.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Text(
            text = "Tambah Siswa",
            style = MaterialTheme.typography.titleLarge
        )

        Spacer(modifier = Modifier.height(12.dp))

        // NIS
        OutlinedTextField(
            value = nis,
            onValueChange = { nis = it },
            label = { Text("NIS") },
            modifier = Modifier.fillMaxWidth()
        )

        // Nama
        OutlinedTextField(
            value = nama,
            onValueChange = { nama = it },
            label = { Text("Nama") },
            modifier = Modifier.fillMaxWidth()
        )

        // Kelas
        OutlinedTextField(
            value = kelas,
            onValueChange = { kelas = it },
            label = { Text("Kelas") },
            modifier = Modifier.fillMaxWidth()
        )

        // Angkatan
        OutlinedTextField(
            value = angkatan,
            onValueChange = { angkatan = it },
            label = { Text("Angkatan") },
            modifier = Modifier.fillMaxWidth()
        )

        // Gender
        OutlinedTextField(
            value = gender,
            onValueChange = { gender = it.uppercase() },
            label = { Text("Gender (MALE / FEMALE)") },
            modifier = Modifier.fillMaxWidth()
        )

        // Checkbox Completed
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Checkbox(
                checked = isCompleted,
                onCheckedChange = { isCompleted = it }
            )
            Text("Is Completed")
        }

        Spacer(modifier = Modifier.height(16.dp))

        // BUTTON SIMPAN
        Button(
            onClick = {

                // VALIDASI
                if (
                    nis.isBlank() ||
                    nama.isBlank() ||
                    kelas.isBlank() ||
                    angkatan.isBlank()
                ) return@Button

                val request = RequestStudent(
                    nis = nis,
                    nama = nama,
                    kelas = kelas.toIntOrNull() ?: 0,
                    angkatan = angkatan.toIntOrNull() ?: 0,
                    gender = gender,
                    isCompleted = if (isCompleted) 1 else 0,

                )

                viewModel.addStudent(request)
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Simpan")
        }

        Spacer(modifier = Modifier.height(16.dp))

        // STATE HANDLING
        when (state) {

            is AddStudentState.Loading -> {
                CircularProgressIndicator()
            }

            is AddStudentState.Success -> {
                Text("Siswa berhasil ditambahkan ✅")

                // reset form
                nis = ""
                nama = ""
                kelas = ""
                angkatan = ""
                gender = "MALE"
                isCompleted = true
            }

            is AddStudentState.Error -> {
                Text((state as AddStudentState.Error).message)
            }

            else -> {}
        }
    }
}