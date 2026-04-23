package com.aditmirzadavid.digitallibrary.data.repository

import com.aditmirzadavid.digitallibrary.data.dto.RequestLoan
import com.aditmirzadavid.digitallibrary.data.service.LoanServerService
import com.aditmirzadavid.digitallibrary.ui.model.Loan

// ==========================================
// LoanServerRepository
// Class Repository yang berfungsi sebagai
// penghubung antara ViewModel dan Service (API).
//
// Bertugas untuk:
// - Mengambil data dari API melalui LoanServerService
// - Mengolah (mapping) data dari DTO ke Model (Loan)
// - Menyediakan data yang siap digunakan oleh UI
// ==========================================
class LoanServerRepository(private val service: LoanServerService) {

    // GET ALL
    suspend fun getAllLoans(): List<Loan> {

        // Memanggil endpoint API untuk mendapatkan data loan dari server.
        val response = service.getLoans()

        if (response.isSuccessful) {
            val body = response.body()
            val loans = body?.data ?: emptyList()

            // Mengubah (mapping) setiap data loan ( peminjaman ) dari ResponseLoan (DTO)
            // menjadi objek Loan (model yang digunakan di UI)
            // Fungsi map digunakan untuk melakukan transformasi pada setiap item
            // dalam list 'loans' menjadi list baru dengan tipe data Loan

            return loans.map { loan ->
                Loan(
                    reg = loan.reg ?: 0,
                    nis = loan.nis,
                    idbuku = loan.idBuku,
                    statusPinjam = loan.statusPinjam
                )
            }
        } else {
            throw Exception("Error getAllLoans: ${response.message()}")
        }
    }

    // GET BY REG
    suspend fun getLoanByReg(reg: Int): Loan {
        val response = service.getLoanByReg(reg)

        if (response.isSuccessful) {
            val loan = response.body()?.data
                ?: throw Exception("Data kosong")

            return Loan(
                idbuku = loan.idBuku,
                nis = loan.nis,
                reg = loan.reg ?: 0,
                statusPinjam = loan.statusPinjam
            )
        } else {
            throw Exception("Error getLoanByReg: ${response.message()}")
        }
    }

    // ADD LOAN
    suspend fun addLoan(request: RequestLoan): Loan {
        val response = service.addLoan(request)

        if (response.isSuccessful) {
            val loan = response.body()?.data
                ?: throw Exception("Response kosong")

            return Loan(
                idbuku = loan.idBuku,
                nis = loan.nis,
                reg = loan.reg ?: 0,
                statusPinjam = loan.statusPinjam
            )
        } else {
            throw Exception("Gagal menambahkan data: ${response.message()}")
        }
    }
}