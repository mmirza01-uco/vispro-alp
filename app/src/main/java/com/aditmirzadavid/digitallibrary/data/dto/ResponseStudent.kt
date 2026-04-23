package com.aditmirzadavid.digitallibrary.data.dto

import com.google.gson.annotations.SerializedName


//data class yang digunakan untuk menampung data buku yang diterima dari server (API)
// sebagai response.
data class ResponseStudent(

    @SerializedName("angkatan")
    val angkatan: Int,

    @SerializedName("gender")
    val gender: String,

    @SerializedName("id")
    val id: Int,

    @SerializedName("isCompleted")
    val isCompleted: Int,

    @SerializedName("kelas")
    val kelas: Int,

    @SerializedName("nama")
    val nama: String,

    @SerializedName("nis")
    val nis: String
)