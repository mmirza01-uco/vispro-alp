package com.aditmirzadavid.digitallibrary.ui.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.aditmirzadavid.digitallibrary.R
import com.aditmirzadavid.digitallibrary.ui.model.Student

@Composable
fun StudentCard(
    student: Student
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
            val imageRes = if (student.gender == "MALE") {
                R.drawable.usermale
            } else {
                R.drawable.userfemale
            }
            Image(
                painter = painterResource(imageRes),
                contentDescription = "Image",
                modifier = Modifier.width(80.dp)
            )
            Column(modifier = Modifier.padding(12.dp)) {
                Text(
                    text = student.nama,
                    style = MaterialTheme.typography.titleMedium
                )

                Text("NIS: ${student.nis}")
                Text("Kelas: ${student.kelas}")
                Text("Angkatan: ${student.angkatan}")
                Text("Gender: ${student.gender}")

                val status = if (student.isCompleted) "Completed" else "Belum"

                Text("Status: $status")
            }

        }

    }
}