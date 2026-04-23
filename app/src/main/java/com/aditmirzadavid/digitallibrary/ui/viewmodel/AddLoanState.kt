package com.aditmirzadavid.digitallibrary.ui.viewmodel

import com.aditmirzadavid.digitallibrary.ui.model.Loan

// ==========================================
// AddLoanState
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
sealed class AddLoanState {
    object Idle : AddLoanState()
    object Loading : AddLoanState()
    data class Success(val data: Loan) : AddLoanState()
    data class Error(val message: String) : AddLoanState()
}