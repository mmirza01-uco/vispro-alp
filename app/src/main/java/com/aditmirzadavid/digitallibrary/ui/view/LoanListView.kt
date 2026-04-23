package com.aditmirzadavid.digitallibrary.ui.view

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.aditmirzadavid.digitallibrary.ui.route.AppView
import com.aditmirzadavid.digitallibrary.ui.viewmodel.LoanListState
import com.aditmirzadavid.digitallibrary.ui.viewmodel.LoanListViewModel

@Composable
fun LoanListView(
    navController: NavHostController,
    viewModel: LoanListViewModel
) {

    // Mengambil data state dari ViewModel dalam bentuk StateFlow,
    // lalu mengubahnya menjadi Compose State agar dapat diamati oleh UI.
    val state by viewModel.state.collectAsState()

    Box(modifier = Modifier.fillMaxSize()) {

        // Setiap state (Loading, Success, Error) akan menghasilkan
        // tampilan UI yang berbeda.
        when (state) {

            is LoanListState.Loading -> {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center)
                )
            }

            is LoanListState.Error -> {
                Text(
                    text = (state as LoanListState.Error).message,
                    modifier = Modifier.align(Alignment.Center)
                )
            }

            is LoanListState.Success -> {

                val loans = (state as LoanListState.Success).data

                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(8.dp)
                ) {
                    // Fungsi items digunakan untuk melakukan iterasi terhadap list 'loans'
                    // dan menampilkan setiap item dalam bentuk UI (LoanCard).
                    items(loans){   loans ->
                        LoanCard(loan = loans)
                    }
                }
            }
        }

        // FAB tambah loan
        FloatingActionButton(
            onClick = {
                navController.navigate(AppView.AddLoan.name)
            },
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp)
        ) {
            Icon(Icons.Default.Add, contentDescription = "Add Loan")
        }
    }
}