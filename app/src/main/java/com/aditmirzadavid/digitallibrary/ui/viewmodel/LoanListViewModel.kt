package com.aditmirzadavid.digitallibrary.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aditmirzadavid.digitallibrary.data.repository.LoanServerRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch


class LoanListViewModel(
    private val repository: LoanServerRepository
) : ViewModel() {

    // Inisialisasi state awal dengan kondisi Loading
    // Artinya saat pertama kali ViewModel dijalankan, UI akan menganggap data sedang dimuat
    private val _state = MutableStateFlow<LoanListState>(LoanListState.Loading)
    val state: StateFlow<LoanListState> = _state

    init {
        loadLoans()
    }

    fun loadLoans() {
        viewModelScope.launch {
            // Ini digunakan untuk memberi tahu UI bahwa proses pengambilan data sedang berlangsung
            _state.value = LoanListState.Loading

            try {
                // Memanggil fungsi repository untuk mengambil seluruh data peminjaman (loan)
                val loans = repository.getAllLoans()
                // Jika data berhasil diambil tanpa error,
                // maka state diubah menjadi Success dan mengirim list loan ke UI
                _state.value = LoanListState.Success(loans)
            } catch (e: Exception) {
                // Jika terjadi error (misalnya jaringan gagal, server error, dll),
                // maka exception akan ditangkap di sini
                _state.value = LoanListState.Error(
                    e.message ?: "Terjadi kesalahan saat memuat data pinjaman"
                )
            }
        }
    }
}