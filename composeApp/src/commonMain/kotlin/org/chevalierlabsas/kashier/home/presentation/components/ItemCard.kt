import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import org.chevalierlabsas.kashier.home.domain.Item
import org.jetbrains.compose.ui.tooling.preview.Preview

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
                Spacer(modifier = Modifier.height(4.dp))
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