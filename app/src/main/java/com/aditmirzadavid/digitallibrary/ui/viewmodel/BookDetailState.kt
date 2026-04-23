package com.aditmirzadavid.digitallibrary.ui.viewmodel

import com.aditmirzadavid.digitallibrary.ui.model.Book

sealed class BookDetailState {
    // State saat data sedang dimuat (loading)
    object Loading : BookDetailState()
    // State saat data berhasil diambil dari API
    data class Success(val data: Book) : BookDetailState()
    // State saat terjadi error
    data class Error(val message: String) : BookDetailState()
}