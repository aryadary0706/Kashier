package org.chevalierlabsas.kashier.home.presentation.components

import SaveButton
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.TextFields
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import kashier.composeapp.generated.resources.Res
import kashier.composeapp.generated.resources.model_name_input
import kashier.composeapp.generated.resources.model_price_input
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview


@Composable
fun ItemBottomSheetContent(
    title: String,
    initialName: String = "",
    initialPrice: String = "",
    onSave: (name: String, price: String) -> Unit
) {
    var name by remember(initialName) { mutableStateOf(initialName) }
    var price by remember(initialPrice) { mutableStateOf(initialPrice) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp)
            .padding(bottom = 32.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold
        )

        // Input Nama Barang
        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            Text("Nama barang", fontWeight = FontWeight.Bold)
            TextField(
                modifier = Modifier.fillMaxWidth(),
                value = name,
                onValueChange = { name = it },
                placeholder = { Text(stringResource(Res.string.model_name_input)) },
                leadingIcon = { Icon(imageVector = Icons.Default.TextFields, contentDescription = null) },
                shape = RoundedCornerShape(12.dp),
                colors = TextFieldDefaults.colors(
                    unfocusedContainerColor = MaterialTheme.colorScheme.tertiaryContainer,
                    focusedContainerColor = MaterialTheme.colorScheme.tertiaryContainer,
                    focusedPlaceholderColor = MaterialTheme.colorScheme.onTertiaryContainer.copy(alpha = 0.6f),
                    unfocusedPlaceholderColor = MaterialTheme.colorScheme.onTertiaryContainer.copy(alpha = 0.6f),
                    focusedLeadingIconColor = MaterialTheme.colorScheme.onTertiaryContainer,
                    unfocusedLeadingIconColor = MaterialTheme.colorScheme.onTertiaryContainer
                )
            )
        }

        // Input Harga Barang
        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            Text("Harga barang", fontWeight = FontWeight.Bold)
            TextField(
                modifier = Modifier.fillMaxWidth(),
                value = price,
                onValueChange = { price = it },
                placeholder = { Text(stringResource(Res.string.model_price_input)) },
                leadingIcon = { Text("Rp.", fontWeight = FontWeight.Bold) },
                shape = RoundedCornerShape(12.dp),
                colors = TextFieldDefaults.colors(
                    unfocusedContainerColor = MaterialTheme.colorScheme.tertiaryContainer,
                    focusedContainerColor = MaterialTheme.colorScheme.tertiaryContainer,
                    focusedLeadingIconColor = MaterialTheme.colorScheme.onTertiaryContainer,
                    unfocusedLeadingIconColor = MaterialTheme.colorScheme.onTertiaryContainer,
                    focusedPlaceholderColor = MaterialTheme.colorScheme.onTertiaryContainer.copy(alpha = 0.6f),
                    unfocusedPlaceholderColor = MaterialTheme.colorScheme.onTertiaryContainer.copy(alpha = 0.6f),
                )
            )
        }

        SaveButton(
            modifier = Modifier.fillMaxWidth(),
            onSave = { onSave(name, price) }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ItemActionBottomSheetPreview() {
    MaterialTheme {
        Surface(
            color = MaterialTheme.colorScheme.surface
        ) {
            ItemBottomSheetContent(
                title = "Tambah Barang",
                initialName = "",
                initialPrice = "",
                onSave = { _, _ -> }
            )
        }
    }
}

@Preview(showBackground = true, name = "Mode Edit")
@Composable
fun ItemActionEditPreview() {
    MaterialTheme {
        Surface(
            color = MaterialTheme.colorScheme.surface
        ) {
            ItemBottomSheetContent(
                title = "Edit Barang",
                initialName = "Sepatu Lari",
                initialPrice = "500.000",
                onSave = { _, _ -> }
            )
        }
    }
}

