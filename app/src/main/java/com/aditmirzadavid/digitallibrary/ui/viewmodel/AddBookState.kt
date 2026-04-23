package com.aditmirzadavid.digitallibrary.ui.viewmodel

import com.aditmirzadavid.digitallibrary.ui.model.Book

// ==========================================
// AddBookState
// Sealed class yang digunakan untuk merepresentasikan
// berbagai kondisi (state) pada proses penambahan buku.
//
// Digunakan oleh ViewModel untuk mengontrol UI,
// sehingga tampilan dapat menyesuaikan kondisi seperti:
// - belum ada aksi
// - sedang loading
// - berhasil
// - gagal (error)
// ==========================================
sealed class AddBookState {
    object Idle : AddBookState()            // belum ada aksi
    object Loading : AddBookState()         // sedang submit
    data class Success(val data: Book) : AddBookState()
    data class Error(val message: String) : AddBookState()
}