package com.aditmirzadavid.digitallibrary.data.dto

// ==========================================
// ResponseLoanDetail
// Data class yang digunakan untuk menampung
// response detail loan ( peminjaman ) dari API.
//
// Struktur ini mengikuti format response backend:
// {
//   "statusCode": 200,
//   "message": "...",
//   "data": { ... }  <-- object ResponseBook
// }
// Digunakan ketika mengambil 1 data peminjaman  berdasarkan reg (GET by reg).
// ==========================================
data class ResponseLoanDetail(
    val data: ResponseLoan,
    val message: String,
    val statusCode: Int
)