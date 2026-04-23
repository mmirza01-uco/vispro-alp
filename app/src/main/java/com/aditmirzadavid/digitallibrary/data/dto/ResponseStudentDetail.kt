package com.aditmirzadavid.digitallibrary.data.dto


// ==========================================
// ResponseStudentDetail
// Data class yang digunakan untuk menampung
// response detail siswa dari API.
//
// Struktur ini mengikuti format response backend:
// {
//   "statusCode": 200,
//   "message": "...",
//   "data": { ... }  <-- object ResponseBook
// }
// Digunakan ketika mengambil 1 data siswa  berdasarkan nis (GET by nis).
// ==========================================
data class ResponseStudentDetail(
    val data: ResponseStudent,
    val message: String,
    val statusCode: Int
)