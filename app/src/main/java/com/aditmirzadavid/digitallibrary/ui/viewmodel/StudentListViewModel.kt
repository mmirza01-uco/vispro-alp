package com.aditmirzadavid.digitallibrary.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aditmirzadavid.digitallibrary.data.repository.StudentServerRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class StudentListViewModel(
    private val repository: StudentServerRepository
) : ViewModel() {

    // Inisialisasi state awal dengan kondisi Loading
    // Artinya saat pertama kali ViewModel dijalankan, UI akan menganggap data sedang dimuat
    private val _state = MutableStateFlow<StudentListState>(StudentListState.Loading)
    val state: StateFlow<StudentListState> = _state

    init {
        loadStudents()
    }

    fun loadStudents() {
        viewModelScope.launch {
            // Ini digunakan untuk memberi tahu UI bahwa proses pengambilan data sedang berlangsung
            _state.value = StudentListState.Loading

            try {
                // Memanggil fungsi repository untuk mengambil seluruh data student
                val students = repository.getAllStudents()
                // Jika data berhasil diambil tanpa error,
                // maka state diubah menjadi Success dan mengirim list loan ke UI
                _state.value = StudentListState.Success(students)
            } catch (e: Exception) {
                // Jika terjadi error (misalnya jaringan gagal, server error, dll),
                // maka exception akan ditangkap di sini
                _state.value = StudentListState.Error(
                    e.message ?: "Terjadi kesalahan saat memuat data siswa"
                )
            }
        }
    }
}