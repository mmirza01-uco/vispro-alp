package com.aditmirzadavid.digitallibrary.ui.viewmodel

import com.aditmirzadavid.digitallibrary.ui.model.Loan

sealed class LoanListState {
    // State saat data sedang dimuat (loading)
    object Loading : LoanListState()
    // State saat data berhasil diambil dari API
    data class Success(val data: List<Loan>) : LoanListState()
    // State saat terjadi error
    data class Error(val message: String) : LoanListState()
}