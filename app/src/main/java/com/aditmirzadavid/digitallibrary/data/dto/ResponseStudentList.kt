package com.aditmirzadavid.digitallibrary.data.dto


// ==========================================
// ResponseSudentList
// Data class yang digunakan untuk menampung response daftar (list) siswa dari API.
//
// Struktur ini mengikuti format response backend:
// {
//   "statusCode": 200,
//   "message": "...",
//   "data": [ ... ]  <-- list of ResponseLoan
// }
// Digunakan ketika mengambil semua data siswa
// ==========================================
data class ResponseStudentList(
    val data: List<ResponseStudent>,
    val message: String,
    val statusCode: Int
)