package com.aditmirzadavid.digitallibrary.data.dto

import com.google.gson.annotations.SerializedName

// Membungkus data input Buku dari user menjadi objek request untuk dikirim ke API
data class RequestBook(

    @SerializedName("deskripsi")
    val deskripsi: String,

    @SerializedName("idbuku")
    val idbuku: String,

    @SerializedName("isbn")
    val isbn: String,

    @SerializedName("judul")
    val judul: String,

    @SerializedName("kategori")
    val kategori: String,

    @SerializedName("penerbit")
    val penerbit: String,

    @SerializedName("pengarang")
    val pengarang: String,

    @SerializedName("posterPath")
    val posterPath: String,

    @SerializedName("posterResId")
    val posterResId: Int,

    @SerializedName("stok")
    val stok: Int,

    @SerializedName("isLiked")
    val isLiked: Int = 0, // default

)