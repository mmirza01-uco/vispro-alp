package com.aditmirzadavid.digitallibrary.data.dto

// ==========================================
// ResponseBookList
// Data class yang digunakan untuk menampung response daftar (list) buku dari API.
//
// Struktur ini mengikuti format response backend:
// {
//   "statusCode": 200,
//   "message": "...",
//   "data": [ ... ]  <-- list of ResponseBook
// }
// Digunakan ketika mengambil semua data buku
// (GET all books).
// ==========================================
data class ResponseBookList(
    val data: List<ResponseBook>,
    val message: String,
    val statusCode: Int
)