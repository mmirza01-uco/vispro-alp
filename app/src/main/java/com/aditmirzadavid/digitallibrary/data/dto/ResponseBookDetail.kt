package com.aditmirzadavid.digitallibrary.data.dto

// ==========================================
// ResponseBookDetail
// Data class yang digunakan untuk menampung
// response detail buku dari API.
//
// Struktur ini mengikuti format response backend:
// {
//   "statusCode": 200,
//   "message": "...",
//   "data": { ... }  <-- object ResponseBook
// }
// Digunakan ketika mengambil 1 data buku  berdasarkan id (GET by id).
// ==========================================
data class ResponseBookDetail(
    val data: ResponseBook,
    val message: String,
    val statusCode: Int
)