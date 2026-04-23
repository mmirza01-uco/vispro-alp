package com.aditmirzadavid.digitallibrary.data.service

import com.aditmirzadavid.digitallibrary.data.dto.RequestBook
import com.aditmirzadavid.digitallibrary.data.dto.ResponseBookDetail
import com.aditmirzadavid.digitallibrary.data.dto.ResponseBookList
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

// ==========================================
// BookServerService
// Interface yang digunakan untuk mendefinisikan
// endpoint API terkait data buku.
//
// Digunakan oleh Retrofit untuk melakukan
// komunikasi HTTP antara aplikasi dan server.
// ==========================================
interface BookServerService {

    // Anotasi @POST dan @GET digunakan untuk menandakan bahwa fungsi ini
    // akan melakukan HTTP request dengan metode POST ke endpoint
    // "api/v1/students" pada server.
    @POST("api/v1/books")
    suspend fun addBook(
        @Body requestBook: RequestBook
    ): Response<ResponseBookDetail> //

    @GET("api/v1/books")
    suspend fun getBooks(): Response<ResponseBookList>

    @GET("api/v1/books/{idbuku}")
    suspend fun getBookById(
        @Path("idbuku") idbuku: String
    ): Response<ResponseBookDetail> //
}




