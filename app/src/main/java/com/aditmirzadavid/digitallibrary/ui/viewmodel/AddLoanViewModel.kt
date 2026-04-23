package com.aditmirzadavid.digitallibrary.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aditmirzadavid.digitallibrary.data.dto.RequestLoan
import com.aditmirzadavid.digitallibrary.data.repository.LoanServerRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class AddLoanViewModel(
    private val repository: LoanServerRepository
) : ViewModel() {

    // _state bersifat private agar hanya dapat diubah dari dalam ViewModel
    // (prinsip encapsulation).
    //
    // Nilai awal di-set ke AddBookState.Idle yang menandakan
    // belum ada proses yang dilakukan.
    private val _state = MutableStateFlow<AddLoanState>(AddLoanState.Idle)
    val state: StateFlow<AddLoanState> = _state

    fun addLoan(request: RequestLoan) {

        // viewModelScope.launch digunakan untuk menjalankan proses secara asynchronous
        // (tidak mengganggu UI thread) di dalam ViewModel.
        //
        // Coroutine ini akan aktif selama ViewModel masih hidup,
        // sehingga aman dari memory leak.
        viewModelScope.launch {
            _state.value = AddLoanState.Loading

            try {
                val result = repository.addLoan(request)
                _state.value = AddLoanState.Success(result)
            } catch (e: Exception) {
                _state.value = AddLoanState.Error(
                    e.message ?: "Gagal menambahkan data peminjaman"
                )
            }
        }
    }

    fun resetState() {
        _state.value = AddLoanState.Idle
    }
}