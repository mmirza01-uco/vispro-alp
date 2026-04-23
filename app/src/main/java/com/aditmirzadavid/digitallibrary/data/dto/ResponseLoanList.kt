package com.aditmirzadavid.digitallibrary.data.dto

// ==========================================
// ResponseLoanList
// Data class yang digunakan untuk menampung response daftar (list) loan dari API.
//
// Struktur ini mengikuti format response backend:
// {
//   "statusCode": 200,
//   "message": "...",
//   "data": [ ... ]  <-- list of ResponseLoan
// }
// Digunakan ketika mengambil semua data peminjaman
// ==========================================
data class ResponseLoanList(
    val data: List<ResponseLoan>,
    val message: String,
    val statusCode: Int
)