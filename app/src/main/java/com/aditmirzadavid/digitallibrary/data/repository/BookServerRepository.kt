package com.aditmirzadavid.digitallibrary.data.repository

import com.aditmirzadavid.digitallibrary.data.dto.RequestBook
import com.aditmirzadavid.digitallibrary.data.service.BookServerService
import com.aditmirzadavid.digitallibrary.ui.model.Book

// ==========================================
// BookServerRepository
// Class Repository yang berfungsi sebagai
// penghubung antara ViewModel dan Service (API).
//
// Bertugas untuk:
// - Mengambil data dari API melalui BookServerService
// - Mengolah (mapping) data dari DTO ke Model (Book)
// - Menyediakan data yang siap digunakan oleh UI
// ==========================================
class BookServerRepository(private val service: BookServerService) {

    // GET ALL BOOKS
    suspend fun getAllBooks(): List<Book> {

        // Memanggil endpoint API untuk mendapatkan data book dari server.
        val response = service.getBooks()

        if (response.isSuccessful) {
            val books = response.body()?.data ?: emptyList()

            // Mengubah (mapping) setiap data buku dari ResponseBook (DTO)
            // menjadi objek Book (model yang digunakan di UI)
            // Fungsi map digunakan untuk melakukan transformasi pada setiap item
            // dalam list 'books' menjadi list baru dengan tipe data Book
            return books.map { book ->
                Book(
                    deskripsi = book.deskripsi,
                    id = book.id,
                    idbuku = book.idbuku,
                    isLiked = book.isLiked == 1,
                    isbn = book.isbn,
                    judul = book.judul,
                    kategori = book.kategori,
                    penerbit = book.penerbit,
                    pengarang = book.pengarang,
                    posterPath = book.posterPath,
                    posterResId = book.posterResId,
                    stok = book.stok
                )
            }
        } else {
            throw Exception("Error getAllBooks: ${response.message()}")
        }
    }

    // GET BOOK BY ID
    suspend fun getBookById(idbuku: String): Book {
        val response = service.getBookById(idbuku)

        if (response.isSuccessful) {
            val book = response.body()?.data
                ?: throw Exception("Data buku kosong")

            return Book(
                deskripsi = book.deskripsi,
                id = book.id,
                idbuku = book.idbuku,
                isLiked = book.isLiked == 1,
                isbn = book.isbn,
                judul = book.judul,
                kategori = book.kategori,
                penerbit = book.penerbit,
                pengarang = book.pengarang,
                posterPath = book.posterPath,
                posterResId = book.posterResId,
                stok = book.stok
            )
        } else {
            throw Exception("Error getBookById: ${response.message()}")
        }
    }

    // ADD BOOK
    suspend fun addBook(request: RequestBook): Book {
        val response = service.addBook(request)

        if (response.isSuccessful) {
            val book = response.body()?.data
                ?: throw Exception("Response kosong")

            return Book(
                deskripsi = book.deskripsi,
                id = book.id,
                idbuku = book.idbuku,
                isLiked = book.isLiked == 1,
                isbn = book.isbn,
                judul = book.judul,
                kategori = book.kategori,
                penerbit = book.penerbit,
                pengarang = book.pengarang,
                posterPath = book.posterPath,
                posterResId = book.posterResId,
                stok = book.stok
            )
        } else {
            throw Exception("Gagal menambahkan buku: ${response.message()}")
        }
    }
}