package com.aditmirzadavid.digitallibrary.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aditmirzadavid.digitallibrary.data.dto.RequestStudent
import com.aditmirzadavid.digitallibrary.data.repository.StudentServerRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class AddStudentViewModel(
    private val repository: StudentServerRepository
) : ViewModel() {

    // _state bersifat private agar hanya dapat diubah dari dalam ViewModel
    // (prinsip encapsulation).
    //
    // Nilai awal di-set ke AddBookState.Idle yang menandakan
    // belum ada proses yang dilakukan.
    private val _state = MutableStateFlow<AddStudentState>(AddStudentState.Idle)
    val state: StateFlow<AddStudentState> = _state

    fun addStudent(request: RequestStudent) {

        // viewModelScope.launch digunakan untuk menjalankan proses secara asynchronous
        // (tidak mengganggu UI thread) di dalam ViewModel.
        //
        // Coroutine ini akan aktif selama ViewModel masih hidup,
        // sehingga aman dari memory leak.
        viewModelScope.launch {
            _state.value = AddStudentState.Loading

            try {
                val result = repository.addStudent(request)
                _state.value = AddStudentState.Success(result)
            } catch (e: Exception) {
                _state.value = AddStudentState.Error(
                    e.message ?: "Gagal menambahkan data siswa"
                )
            }
        }
    }

    fun resetState() {
        _state.value = AddStudentState.Idle
    }
}