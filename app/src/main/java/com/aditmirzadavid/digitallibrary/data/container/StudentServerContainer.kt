package com.aditmirzadavid.digitallibrary.data.container


import com.aditmirzadavid.digitallibrary.data.repository.StudentServerRepository
import com.aditmirzadavid.digitallibrary.data.service.StudentServerService
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object StudentServerContainer {

    // Base URL API (alamat server backend)
    private const val BASE_URL = "http://192.168.1.9:8080/"

    // Membuat instance OkHttpClient yang digunakan sebagai HTTP client
    // untuk menangani komunikasi jaringan (network request).
    //
    // OkHttpClient bertugas untuk:
    // - Mengirim request ke server
    // - Menerima response dari server
    // - Mengatur timeout, interceptor, logging, dll (jika dikonfigurasi)
    private val client = OkHttpClient.Builder().build()

    // Membuat instance Retrofit untuk komunikasi HTTP
    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    // Membuat instance service API (interface Retrofit)
    val studentService: StudentServerService =
        retrofit.create(StudentServerService::class.java)

    // Membuat instance repository
    // yang akan digunakan oleh ViewModel
    val studentRepository = StudentServerRepository(studentService)
}