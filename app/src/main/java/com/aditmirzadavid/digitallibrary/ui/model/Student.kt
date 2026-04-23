package com.aditmirzadavid.digitallibrary.ui.model

// ==========================================
// Student (Model UI)
// Data class yang digunakan untuk merepresentasikan
// data student di dalam aplikasi (UI layer).
// ==========================================
data class Student(
    val id: Int,
    val nis: String,
    val nama: String,
    val kelas: String,
    val angkatan: String,
    val gender: String,
    val isCompleted: Boolean
)