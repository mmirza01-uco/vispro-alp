package com.aditmirzadavid.digitallibrary.ui.model


// ==========================================
// Loan (Model UI)
// Data class yang digunakan untuk merepresentasikan
// data loan (peminjaman) di dalam aplikasi (UI layer).
// ==========================================
data class Loan(
    val idbuku: String,
    val nis: String,
    val reg: Int,
    val statusPinjam: Int
)
