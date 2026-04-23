# 📚 Digital Library

Aplikasi manajemen perpustakaan sekolah berbasis **Android (Jetpack Compose)** yang terhubung dengan **REST API Spring Boot**. Sistem ini memudahkan admin perpustakaan dalam mengelola data buku, anggota siswa, dan transaksi peminjaman secara digital.

> **Mata Kuliah:** Visual Programming — Informatics UC Online Batch 6  
> **Tim:** David Pieter Raimond · Mirza Ilham Tontowi · Adid Kurniawan Wicaksono

---

## Daftar Isi

- [Prasyarat](#-prasyarat)
- [Menjalankan Back-End (Spring Boot)](#-menjalankan-back-end-spring-boot)
- [Menjalankan Front-End (Android)](#-menjalankan-front-end-android)
- [Struktur Proyek](#-struktur-proyek)
- [Fitur Aplikasi](#-fitur-aplikasi)
- [Endpoint API](#-endpoint-api)

---

## Prasyarat

Pastikan semua tools berikut sudah terinstal sebelum menjalankan proyek.

### Back-End
| Tool | Versi | Keterangan |
|------|-------|------------|
| Java JDK | 17+ | [Download](https://adoptium.net/) |
| Maven | 3.8+ | Biasanya sudah termasuk di IDE |
| XAMPP | Terbaru | Untuk menjalankan MySQL server lokal |
| IntelliJ IDEA / VS Code | Terbaru | IDE disarankan |

### Front-End
| Tool | Versi | Keterangan |
|------|-------|------------|
| Android Studio | Hedgehog+ | [Download](https://developer.android.com/studio) |
| JDK | 17+ | Sudah termasuk di Android Studio |
| Android SDK | API 26+ | Install via SDK Manager di Android Studio |
| Emulator / Device | Android 8.0+ | Untuk menjalankan aplikasi |

---

## Menjalankan Back-End (Spring Boot)

### 1. Siapkan Database MySQL via XAMPP

1. Buka **XAMPP Control Panel**, klik **Start** pada module **Apache** dan **MySQL**
2. Buka browser, akses `http://localhost/phpmyadmin`
3. Buat database baru:
   ```sql
   CREATE DATABASE digitallibrary;
   ```

### 2. Konfigurasi Koneksi Database

Buka file `src/main/resources/application.properties`, sesuaikan dengan konfigurasi lokal Anda:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/digitallibrary
spring.datasource.username=root
spring.datasource.password=
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQLDialect
```

> **Catatan:** Secara default XAMPP menggunakan username `root` tanpa password. Sesuaikan jika Anda menggunakan konfigurasi berbeda.

### 3. Clone & Buka Proyek Back-End

```bash
# Clone repository (jika belum)
git clone <url-repository>
cd <nama-folder-project>/REST\ API
```

Atau buka langsung folder `REST API` menggunakan IntelliJ IDEA / VS Code.

### 4. Build & Jalankan

**Menggunakan Maven CLI:**
```bash
# Install dependencies & build
./mvnw clean install

# Jalankan server
./mvnw spring-boot:run
```

**Menggunakan IntelliJ IDEA:**
1. Buka proyek → tunggu Maven selesai indexing
2. Temukan file `DigitallibraryApplication.java`
3. Klik tombol **▶ Run** di pojok kanan atas

**Menggunakan VS Code:**
1. Install extension **Spring Boot Extension Pack**
2. Buka Command Palette → `Spring Boot: Run`

### 5. Verifikasi Server Berjalan

Server berjalan secara default di port **8080**. Cek dengan membuka:
```
http://localhost:8080
```

Atau gunakan **Postman** untuk mengakses salah satu endpoint:
```
GET http://localhost:8080/api/books
```

---

## Menjalankan Front-End (Android)

### 1. Buka Proyek di Android Studio

1. Buka **Android Studio**
2. Pilih **Open** → arahkan ke folder `Jetpack Compose` (folder Android project)
3. Tunggu hingga proses **Gradle Sync** selesai (bisa beberapa menit pertama kali)

### 2. Sesuaikan URL Base API

> **Penting:** Emulator Android tidak bisa mengakses `localhost` komputer secara langsung.

Temukan file konfigurasi API (biasanya di ViewModel atau file konstanta), ubah base URL sesuai kondisi Anda:

| Kondisi | Base URL |
|---------|----------|
| Emulator Android | `http://10.0.2.2:8080/` |
| Device fisik (USB) | `http://<IP-komputer-Anda>:8080/` |
| Device fisik (WiFi) | `http://<IP-komputer-Anda>:8080/` (pastikan 1 jaringan) |

Untuk mengetahui IP komputer Anda:
```bash
# Windows
ipconfig

# macOS / Linux
ifconfig
```

### 3. Pastikan Back-End Sudah Berjalan

Sebelum menjalankan aplikasi Android, pastikan server Spring Boot sudah aktif (lihat langkah sebelumnya).

### 4. Jalankan Aplikasi

**Menggunakan Emulator:**
1. Buka **Device Manager** di Android Studio
2. Buat virtual device baru (disarankan: Pixel 6, API 33+)
3. Klik **▶ Run** atau tekan `Shift + F10`

**Menggunakan Device Fisik:**
1. Aktifkan **Developer Options** di perangkat Android Anda
2. Aktifkan **USB Debugging**
3. Sambungkan perangkat via kabel USB
4. Pilih perangkat Anda di dropdown device, klik **▶ Run**

---

## Struktur Proyek

```
digitallibrary/
│
├── REST API/                          # Back-End Spring Boot
│   └── src/main/java/com/aditmirzadavid/digitallibrary/
│       ├── controller/
│       │   ├── BookController.java
│       │   ├── LoanController.java
│       │   └── StudentController.java
│       ├── dto/
│       │   ├── BookRequestDto.java    & BookResponseDto.java
│       │   ├── LoanRequestDto.java    & LoanResponseDto.java
│       │   ├── StudentRequestDto.java & StudentResponseDto.java
│       │   └── Response.java
│       ├── entity/
│       │   ├── Book.java
│       │   ├── Loan.java
│       │   └── Student.java
│       ├── repositori/
│       │   ├── BookRepository.java
│       │   ├── LoanRepository.java
│       │   └── StudentRepository.java
│       └── service/
│           ├── impl/
│           │   ├── BookServiceImpl.java
│           │   ├── LoanServiceImpl.java
│           │   └── StudentServiceImpl.java
│           ├── IBookService.java
│           ├── ILoanService.java
│           └── IStudentService.java
│
└── Jetpack Compose/                   # Front-End Android
    └── app/src/main/java/com/aditmirzadavid/digitallibrary/
        └── ui/
            ├── model/
            │   ├── Book.kt            & DummyBookData.kt
            │   ├── Student.kt         & DummyStudentData.kt
            │   └── Loan.kt            & DummyLoanData.kt
            ├── route/
            │   └── AppRouting.kt
            ├── theme/
            ├── view/
            │   ├── BookListView.kt    & BookDetailView.kt
            │   ├── LoanListView.kt    & StudentListView.kt
            │   ├── AddBookView.kt     & AddLoanView.kt
            │   ├── AddStudentView.kt
            │   └── BookCard.kt  /  LoanCard.kt  /  StudentCard.kt
            └── viewmodel/
                ├── BookListViewModel.kt   & BookDetailViewModel.kt
                ├── LoanListViewModel.kt   & StudentListViewModel.kt
                ├── AddBookViewModel.kt    & AddLoanViewModel.kt
                └── AddStudentViewModel.kt
```

---

## Fitur Aplikasi

| Fitur | Deskripsi |
|-------|-----------|
| List Buku | Lihat semua buku dengan info stok, ISBN, dan kategori |
| Detail Buku | Tampilan detail buku lengkap dengan cover dan deskripsi |
| List Peminjaman | Pantau semua transaksi peminjaman aktif |
| List Siswa | Kelola data anggota perpustakaan |
| Tambah Buku | Form input pendaftaran buku baru |
| Tambah Peminjaman | Catat transaksi peminjaman baru (NIS + ID Buku) |
| Tambah Siswa | Daftarkan siswa sebagai anggota perpustakaan |

---

## Endpoint API

Base URL: `http://localhost:8080`

### Buku

| Method | Endpoint | Deskripsi |
|--------|----------|-----------|
| `GET` | `/api/books` | Ambil semua data buku |
| `GET` | `/api/books/{id}` | Ambil detail buku by ID |
| `POST` | `/api/books` | Tambah buku baru |
| `PUT` | `/api/books/{id}` | Update data buku |
| `DELETE` | `/api/books/{id}` | Hapus buku |

### Siswa

| Method | Endpoint | Deskripsi |
|--------|----------|-----------|
| `GET` | `/api/students` | Ambil semua data siswa |
| `GET` | `/api/students/{nis}` | Ambil detail siswa by NIS |
| `POST` | `/api/students` | Tambah siswa baru |
| `PUT` | `/api/students/{nis}` | Update data siswa |
| `DELETE` | `/api/students/{nis}` | Hapus siswa |

### Peminjaman

| Method | Endpoint | Deskripsi |
|--------|----------|-----------|
| `GET` | `/api/loans` | Ambil semua data peminjaman |
| `GET` | `/api/loans/{reg}` | Ambil detail peminjaman by nomor registrasi |
| `POST` | `/api/loans` | Catat peminjaman baru |
| `PUT` | `/api/loans/{reg}` | Update status peminjaman (kembalikan buku) |
| `DELETE` | `/api/loans/{reg}` | Hapus data peminjaman |

### Contoh Request Body

**POST /api/books**
```json
{
  "idbuku": "ID26005",
  "judul": "Clean Code",
  "pengarang": "Robert C. Martin",
  "deskripsi": "A handbook of agile software craftsmanship",
  "kategori": "Teknologi",
  "penerbit": "Prentice Hall",
  "isbn": "9780132350884",
  "stok": 3
}
```

**POST /api/students**
```json
{
  "nis": "11012",
  "nama": "Budi Santoso",
  "kelas": "10",
  "angkatan": "2025",
  "gender": "Male"
}
```

**POST /api/loans**
```json
{
  "nis": "11012",
  "idbuku": "ID26005"
}
```

---

## Troubleshooting

**`Connection refused` saat aplikasi Android mencoba konek ke API**
- Pastikan server Spring Boot sudah berjalan
- Pastikan menggunakan `10.0.2.2` (bukan `localhost`) jika pakai emulator
- Cek firewall tidak memblokir port 8080

**`Unknown database 'digitallibrary'`**
- Pastikan XAMPP MySQL sudah running
- Buat database `digitallibrary` terlebih dahulu via phpMyAdmin

**Gradle Sync gagal di Android Studio**
- Pastikan koneksi internet aktif (untuk download dependencies)
- Coba: `File → Invalidate Caches → Restart`

**`Port 8080 already in use`**
- Ganti port di `application.properties`: `server.port=8081`
- Sesuaikan juga base URL di aplikasi Android