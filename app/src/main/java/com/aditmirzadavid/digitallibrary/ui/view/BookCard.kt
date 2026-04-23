package com.aditmirzadavid.digitallibrary.ui.view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.aditmirzadavid.digitallibrary.ui.model.Book

@Composable
fun BookCard(
    book: Book,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp)
            .clickable { onClick() }, //
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(modifier = Modifier.padding(12.dp)) {

            // AsyncImage digunakan untuk menampilkan gambar dari URL (internet)
            // secara asynchronous menggunakan library Coil.
            val imageUrl = "http://192.168.1.9/elibrary/poster/${book.posterPath}"
            AsyncImage(
                model = imageUrl,
                contentDescription = book.judul,
                modifier = Modifier
                    .size(80.dp)
            )

            Spacer(modifier = Modifier.width(12.dp))

            Column(modifier = Modifier.weight(1f)) {

                Text(
                    text = book.judul,
                    style = MaterialTheme.typography.titleMedium
                )

                Text(
                    text = "ID Buku: ${book.idbuku}",
                    style = MaterialTheme.typography.bodySmall
                )

                Text(
                    text = "Penulis: ${book.pengarang}",
                    style = MaterialTheme.typography.bodySmall
                )

                Text(
                    text = "Kategori: ${book.kategori}",
                    style = MaterialTheme.typography.bodySmall
                )

                Text(
                    text = "Stok: ${book.stok}",
                    style = MaterialTheme.typography.bodySmall
                )
            }
        }
    }
}