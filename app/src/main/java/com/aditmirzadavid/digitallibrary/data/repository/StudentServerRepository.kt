package com.aditmirzadavid.digitallibrary.data.repository

import com.aditmirzadavid.digitallibrary.data.dto.RequestStudent
import com.aditmirzadavid.digitallibrary.data.service.StudentServerService
import com.aditmirzadavid.digitallibrary.ui.model.Student

// ==========================================
// StudentServerRepository
// Class Repository yang berfungsi sebagai
// penghubung antara ViewModel dan Service (API).
//
// Bertugas untuk:
// - Mengambil data dari API melalui StudentServerService
// - Mengolah (mapping) data dari DTO ke Model (Student)
// - Menyediakan data yang siap digunakan oleh UI
// ==========================================
class StudentServerRepository(private val service: StudentServerService) {

    // GET ALL
    suspend fun getAllStudents(): List<Student> {

        // Memanggil endpoint API untuk mendapatkan data student dari server.
        val response = service.getStudents()

        if (response.isSuccessful) {
            val students = response.body()?.data ?: emptyList()

            // Mengubah (mapping) setiap data student dari ResponseStudent (DTO)
            // menjadi objek Student (model yang digunakan di UI)
            // Fungsi map digunakan untuk melakukan transformasi pada setiap item
            // dalam list 'students' menjadi list baru dengan tipe data Student

            return students.map { student ->
                Student(
                    id = student.id,
                    nis = student.nis,
                    nama = student.nama,
                    kelas = student.kelas.toString(),
                    angkatan = student.angkatan.toString(),
                    gender = student.gender,
                    isCompleted = student.isCompleted == 1
                )
            }
        } else {
            throw Exception("Error getAllStudents: ${response.message()}")
        }
    }

    // GET BY NIS
    suspend fun getStudentByNis(nis: String): Student {
        val response = service.getStudentByNis(nis)

        if (response.isSuccessful) {
            val student = response.body()?.data
                ?: throw Exception("Data siswa kosong")

            return Student(
                id = student.id,
                nis = student.nis,
                nama = student.nama,
                kelas = student.kelas.toString(),
                angkatan = student.angkatan.toString(),
                gender = student.gender,
                isCompleted = student.isCompleted == 1
            )
        } else {
            throw Exception("Error getStudentByNis: ${response.message()}")
        }
    }

    // ADD STUDENT
    suspend fun addStudent(request: RequestStudent): Student {
        val response = service.addStudent(request)

        if (response.isSuccessful) {
            val student = response.body()?.data
                ?: throw Exception("Response kosong")

            return Student(
                id = student.id,
                nis = student.nis,
                nama = student.nama,
                kelas = student.kelas.toString(),
                angkatan = student.angkatan.toString(),
                gender = student.gender,
                isCompleted = student.isCompleted == 1
            )
        } else {
            throw Exception("Gagal menambahkan data siswa: ${response.message()}")
        }
    }
}