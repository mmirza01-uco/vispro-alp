package com.aditmirzadavid.digitallibrary.ui.viewmodel

import com.aditmirzadavid.digitallibrary.ui.model.Book

sealed class BookListState {
    // State saat data sedang dimuat (loading)
    object Loading : BookListState()
    // State saat data berhasil diambil dari API
    data class Success(val data: List<Book>) : BookListState()
    // State saat terjadi error
    data class Error(val message: String) : BookListState()
}