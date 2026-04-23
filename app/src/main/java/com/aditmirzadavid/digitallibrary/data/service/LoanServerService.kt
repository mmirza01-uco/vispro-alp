package com.aditmirzadavid.digitallibrary.data.service

import com.aditmirzadavid.digitallibrary.data.dto.RequestLoan
import com.aditmirzadavid.digitallibrary.data.dto.ResponseLoanDetail
import com.aditmirzadavid.digitallibrary.data.dto.ResponseLoanList
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
interface LoanServerService {

    // Anotasi @POST dan @GET digunakan untuk menandakan bahwa fungsi ini
    // akan melakukan HTTP request dengan metode POST ke endpoint
    // "api/v1/loans" pada server.
    @POST("api/v1/loans")
    suspend fun addLoan(
        @Body requestLoan: RequestLoan
    ): Response<ResponseLoanDetail>

    @GET("api/v1/loans")
    suspend fun getLoans(): Response<ResponseLoanList>

    @GET("api/v1/loans/{reg}")
    suspend fun getLoanByReg(
        @Path("reg") reg: Int
    ): Response<ResponseLoanDetail>
}