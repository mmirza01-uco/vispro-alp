package com.aditmirzadavid.digitallibrary.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aditmirzadavid.digitallibrary.data.dto.RequestBook
import com.aditmirzadavid.digitallibrary.data.repository.BookServerRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class AddBookViewModel(
    private val repository: BookServerRepository
) : ViewModel() {

    // _state bersifat private agar hanya dapat diubah dari dalam ViewModel
    // (prinsip encapsulation).
    //
    // Nilai awal di-set ke AddBookState.Idle yang menandakan
    // belum ada proses yang dilakukan.
    private val _state = MutableStateFlow<AddBookState>(AddBookState.Idle)
    val state: StateFlow<AddBookState> = _state

    fun addBook(request: RequestBook) {

        // viewModelScope.launch digunakan untuk menjalankan proses secara asynchronous
        // (tidak mengganggu UI thread) di dalam ViewModel.
        //
        // Coroutine ini akan aktif selama ViewModel masih hidup,
        // sehingga aman dari memory leak.
        viewModelScope.launch {
            _state.value = AddBookState.Loading

            try {
                val result = repository.addBook(request)
                _state.value = AddBookState.Success(result)
            } catch (e: Exception) {
                _state.value = AddBookState.Error(
                    e.message ?: "Gagal menambahkan buku"
                )
            }
        }
    }

    fun resetState() {
        _state.value = AddBookState.Idle
    }
}