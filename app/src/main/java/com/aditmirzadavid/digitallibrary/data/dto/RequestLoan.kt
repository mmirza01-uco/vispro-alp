package com.aditmirzadavid.digitallibrary.data.dto

import com.google.gson.annotations.SerializedName

// Membungkus data input Peminjaman dari user menjadi objek request untuk dikirim ke API
data class RequestLoan(

    @SerializedName("idbuku")
    val idbuku: String,

    @SerializedName("nis")
    val nis: String,

    @SerializedName("statusPinjam")
    val statusPinjam: Int,
)