package com.aditmirzadavid.digitallibrary.data.dto

import com.google.gson.annotations.SerializedName

// Membungkus data input Siswa dari user menjadi objek request untuk dikirim ke API
data class RequestStudent(

    @SerializedName("angkatan")
    val angkatan: Int,

    @SerializedName("gender")
    val gender: String,

    @SerializedName("kelas")
    val kelas: Int,

    @SerializedName("nama")
    val nama: String,

    @SerializedName("nis")
    val nis: String, // Set nis as the PRIMARY KEY

    @SerializedName("isCompleted")
    val isCompleted: Int = 1
)