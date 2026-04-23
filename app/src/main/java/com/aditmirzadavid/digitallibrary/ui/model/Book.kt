package com.aditmirzadavid.digitallibrary.ui.model

// ==========================================
// Book (Model UI)
// Data class yang digunakan untuk merepresentasikan
// data buku di dalam aplikasi (UI layer).
// ==========================================
data class Book(
    val deskripsi: String,
    val id: Int,
    val idbuku: String,
    val isLiked: Boolean,
    val isbn: String,
    val judul: String,
    val kategori: String,
    val penerbit: String,
    val pengarang: String,
    val posterPath: String,
    val posterResId: Int,
    val stok: Int
)