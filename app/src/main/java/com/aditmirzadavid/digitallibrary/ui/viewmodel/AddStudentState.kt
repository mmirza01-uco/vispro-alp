package com.aditmirzadavid.digitallibrary.ui.viewmodel

import com.aditmirzadavid.digitallibrary.ui.model.Student


// ==========================================
// AddStudentState
// Sealed class yang digunakan untuk merepresentasikan
// berbagai kondisi (state) pada proses penambahan loan.
//
// Digunakan oleh ViewModel untuk mengontrol UI,
// sehingga tampilan dapat menyesuaikan kondisi seperti:
// - belum ada aksi
// - sedang loading
// - berhasil
// - gagal (error)
// ==========================================
sealed class AddStudentState {
    object Idle : AddStudentState()
    object Loading : AddStudentState()
    data class Success(val data: Student) : AddStudentState()
    data class Error(val message: String) : AddStudentState()
}