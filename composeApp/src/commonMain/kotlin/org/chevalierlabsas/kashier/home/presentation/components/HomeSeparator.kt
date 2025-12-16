package org.chevalierlabsas.kashier.home.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun HomeSeparator(
    title: String,
    visible: Boolean,
    onAction: (visible: Boolean) -> Unit,
    modifier: Modifier = Modifier
) {

    val dividerColor = MaterialTheme.colorScheme.onSurfaceVariant
    val icon: ImageVector = if (visible) {
        Icons.Default.KeyboardArrowDown
    } else {
        Icons.AutoMirrored.Filled.KeyboardArrowRight
    }

    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Min)
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Teks Kategori
        Text(
            text = title,
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onBackground, // Teks terlihat jelas
            modifier = Modifier.padding(start = 16.dp)
        )

        // Spacer untuk mendorong elemen ke sisi
        Spacer(modifier = Modifier.width(8.dp))

        // Divider Line
        Box(
            modifier = Modifier
                .weight(1f) // Mengisi sisa ruang secara horizontal
                .height(2.dp) // Ketebalan garis
                .background(dividerColor)
        )

        // Tombol Toggle (Panah)
        IconButton(
            onClick = { onAction(!visible) },
            modifier = Modifier.padding(end = 8.dp)
        ) {
            Icon(
                imageVector = icon,
                contentDescription = if (visible) "Sembunyikan" else "Tampilkan",
                tint = MaterialTheme.colorScheme.onSurface
            )
        }
    }
}

@Preview
@Composable
fun DividerPreview() {
    MaterialTheme {
        Box(
            modifier = Modifier.fillMaxWidth().background(Color.White).padding(16.dp),
        ){
            Column {
                HomeSeparator(
                    title = "Semua barang",
                    visible = true,
                    onAction = { }
                )
            }
        }
    }
}