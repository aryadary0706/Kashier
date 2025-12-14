import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.ui.tooling.preview.Preview

data class Item(
    val id: Int,
    val userId:Int,
    val name:String,
    val price: Double
)

@Composable
fun ItemCard(
    item: Item,
    onEditClick: () -> Unit,
    onAddClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val contentColor = MaterialTheme.colorScheme.onSecondary

    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.secondary,
            contentColor = contentColor
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            // Mengatur elemen agar terpisah (item di kiri, tombol di kanan)
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Kolom untuk Nama dan Harga
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = item.name,
                    style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Medium),
                    color = contentColor
                )
                Spacer(modifier = Modifier.height(12.dp))
                Text(
                    // Format sederhana untuk harga. Perlu penyesuaian jika ingin format mata uang penuh.
                    text = "Rp. ${item.price}",
                    style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold),
                    color = contentColor
                )
            }

            // Baris untuk Tombol Edit dan Tambah
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Tombol Edit
                IconButton(
                    onClick = onEditClick
                ) {
                    Icon(
                        imageVector = Icons.Default.Edit,
                        contentDescription = "Edit Item",
                        tint = contentColor
                    )
                }
                // Tombol Tambah
                IconButton(
                    onClick = onAddClick
                ) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = "Tambah Item",
                        tint = contentColor
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun DomainPreview() {
    // Contoh penggunaan dengan data dummy
    val dummyItem = Item(
        id = 1,
        userId = 1,
        name = "Telur 0.5 KG",
        price = 12000.0
    )
    MaterialTheme {
        ItemCard(
            item = dummyItem,
            onEditClick = { println("Edit clicked") },
            onAddClick = { println("Add clicked") },
            modifier = Modifier.padding(16.dp)
        )
    }
}

@Composable
fun Divider(
    categoryName: String,
    isExpanded: Boolean, // Status tampilkan/sembunyikan
    onToggleClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    // Menggunakan warna onSurface atau onBackground sesuai permintaan
    val dividerColor = MaterialTheme.colorScheme.onSurfaceVariant
    val icon: ImageVector = if (isExpanded) {
        Icons.Default.KeyboardArrowDown
    } else {
        Icons.AutoMirrored.Filled.KeyboardArrowRight // Menggunakan AutoMirrored untuk panah kanan yang benar
    }

    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Min) // Membuat tinggi se-minimal mungkin sesuai konten
            .clickable(onClick = onToggleClick) // Seluruh baris bisa diklik
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Teks Kategori
        Text(
            text = categoryName,
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
            onClick = onToggleClick,
            modifier = Modifier.padding(end = 8.dp)
        ) {
            Icon(
                imageVector = icon,
                contentDescription = if (isExpanded) "Sembunyikan" else "Tampilkan",
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
                Divider(
                    categoryName = "Semua barang",
                    isExpanded = true,
                    onToggleClick = { /* Tampilkan/Sembunyikan */ }
                )
            }
        }
    }
}