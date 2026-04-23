package com.aditmirzadavid.digitallibrary.ui.viewmodel

import com.aditmirzadavid.digitallibrary.ui.model.Student

sealed class StudentListState {
    // State saat data sedang dimuat (loading)
    object Loading : StudentListState()
    // State saat data berhasil diambil dari API
    data class Success(val data: List<Student>) : StudentListState()
    // State saat terjadi error
    data class Error(val message: String) : StudentListState()
}