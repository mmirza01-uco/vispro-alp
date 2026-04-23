package com.aditmirzadavid.digitallibrary.data.service

import com.aditmirzadavid.digitallibrary.data.dto.RequestStudent
import com.aditmirzadavid.digitallibrary.data.dto.ResponseStudentDetail
import com.aditmirzadavid.digitallibrary.data.dto.ResponseStudentList
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
interface StudentServerService {

    // Anotasi @POST dan @GET digunakan untuk menandakan bahwa fungsi ini
    // akan melakukan HTTP request dengan metode POST ke endpoint
    // "api/v1/students" pada server.
    @POST("api/v1/students")
    suspend fun addStudent(
        @Body requestStudent: RequestStudent
    ): Response<ResponseStudentDetail>

    @GET("api/v1/students")
    suspend fun getStudents(): Response<ResponseStudentList>

    @GET("api/v1/students/{nis}")
    suspend fun getStudentByNis(
        @Path("nis") nis: String
    ): Response<ResponseStudentDetail>
}