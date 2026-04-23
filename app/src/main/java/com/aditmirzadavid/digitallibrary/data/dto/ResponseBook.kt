package com.aditmirzadavid.digitallibrary.data.dto

import com.google.gson.annotations.SerializedName

// Data class yang digunakan untuk menampung data buku yang diterima dari server (API)
// sebagai response.
data class ResponseBook(

    @SerializedName("deskripsi")
    val deskripsi: String,

    @SerializedName("id")
    val id: Int,

    @SerializedName("idbuku")
    val idbuku: String,

    @SerializedName("isLiked")
    val isLiked: Int,

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
    val stok: Int
)