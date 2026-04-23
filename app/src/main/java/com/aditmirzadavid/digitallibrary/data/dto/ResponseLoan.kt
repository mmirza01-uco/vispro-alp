package com.aditmirzadavid.digitallibrary.data.dto

import com.google.gson.annotations.SerializedName

//data class yang digunakan untuk menampung data buku yang diterima dari server (API)
// sebagai response.
data class ResponseLoan(
    @SerializedName("idbuku")
    val idBuku: String,

    @SerializedName("nis")
    val nis: String,

    @SerializedName("reg")
    val reg: Int?,

    @SerializedName("statusPinjam")
    val statusPinjam: Int,

    @SerializedName("tanggalPinjam")
    val tanggalPinjam: String?
)