package com.aditmirzadavid.digitallibrary.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aditmirzadavid.digitallibrary.data.repository.BookServerRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class BookListViewModel(
    private val repository: BookServerRepository
) : ViewModel() {

    // Inisialisasi state awal dengan kondisi Loading
    // Artinya saat pertama kali ViewModel dijalankan, UI akan menganggap data sedang dimuat
    private val _state = MutableStateFlow<BookListState>(BookListState.Loading)
    val state: StateFlow<BookListState> = _state

    init {
        loadBooks()
    }

    fun loadBooks() {
        viewModelScope.launch {
            // Ini digunakan untuk memberi tahu UI bahwa proses pengambilan data sedang berlangsung
            _state.value = BookListState.Loading

            try {
                // Memanggil fungsi repository untuk mengambil seluruh data buku
                val books = repository.getAllBooks()
                // Jika data berhasil diambil tanpa error,
                // maka state diubah menjadi Success dan mengirim list buku ke UI
                _state.value = BookListState.Success(books)
            } catch (e: Exception) {
                // Jika terjadi error (misalnya jaringan gagal, server error, dll),
                // maka exception akan ditangkap di sini
                _state.value = BookListState.Error(
                    e.message ?: "Terjadi kesalahan"
                )
            }
        }
    }
}