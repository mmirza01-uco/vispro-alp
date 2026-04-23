package com.aditmirzadavid.digitallibrary.ui.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.DoubleArrow
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.aditmirzadavid.digitallibrary.ui.model.Loan
@Composable
fun LoanCard(
    loan: Loan
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(Icons.Default.DoubleArrow, contentDescription = "Tambah Buku")
            Column(modifier = Modifier.padding(12.dp)) {

                Text(
                    text = "Buku: ${loan.idbuku}",
                    style = MaterialTheme.typography.titleMedium
                )

                Text("NIS: ${loan.nis}")
                Text("Reg: ${loan.reg}")

                val status = if (loan.statusPinjam == 1) {
                    "Dipinjam"
                } else {
                    "Dikembalikan"
                }

                Text("Status: $status")
            }
        }

    }
}