package com.aditmirzadavid.digitallibrary.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aditmirzadavid.digitallibrary.data.repository.BookServerRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class BookDetailViewModel(
    private val repository: BookServerRepository
) : ViewModel() {

    private val _state = MutableStateFlow<BookDetailState>(BookDetailState.Loading)
    val state: StateFlow<BookDetailState> = _state

    fun getBookById(idbuku: String) {
        viewModelScope.launch {
            _state.value = BookDetailState.Loading
            try {
                // Memanggil fungsi repository untuk mengambil data buku berdasarkan id
                val book = repository.getBookById(idbuku)
                _state.value = BookDetailState.Success(book)
            } catch (e: Exception) {
                // Jika terjadi error (misalnya: koneksi gagal, data tidak ditemukan, dll),
                // Ambil pesan error dari exception, jika null gunakan default "Error"
                _state.value = BookDetailState.Error(e.message ?: "Error")
            }
        }
    }
}