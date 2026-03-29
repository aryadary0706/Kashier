package org.chevalierlabsas.kashier.history.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import kashier.composeapp.generated.resources.Res
import kashier.composeapp.generated.resources.history_topbar
import kashier.composeapp.generated.resources.navigate_back
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.daysUntil
import kotlinx.datetime.toLocalDateTime
import org.chevalierlabsas.kashier.history.domain.histItem
import org.chevalierlabsas.kashier.history.presentation.components.HistoryCard
import org.chevalierlabsas.kashier.home.data.DummyDataSource
import org.jetbrains.compose.resources.stringResource
import kotlin.time.Clock

@OptIn(kotlin.time.ExperimentalTime::class)
@Composable
fun getGroupedHistory(items: List<histItem>): Map<String, List<histItem>> {
    val now = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()).date

    return items.groupBy { item ->
        try {
            // Parsing string tanggal ke LocalDate (Asumsi format: "2026-03-29")
            val itemDate = LocalDate.parse(item.tanggal)
            val daysCategory = itemDate.daysUntil(now)

            when {
                daysCategory == 0 -> "Hari ini"
                daysCategory in 1..7 -> "Minggu ini"
                itemDate.month == now.month && itemDate.year == now.year -> "Bulan ini"
                else -> "Sudah Lama"
            }
        } catch (e: Exception) {
            "Lainnya"
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HistoryScreen(
    onNavigateBack: () -> Unit
) {
    val historyData = DummyDataSource().gethistory();
    val groupedHistory = getGroupedHistory(historyData)
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    // buat sebuah string di strings.xml dengan value "Riwayat Transaksi"
                    Text(stringResource(Res.string.history_topbar))
                },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = stringResource(Res.string.navigate_back)
                        )
                    }
                }
            )
        }
    ) {
        padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(horizontal =16.dp),
            verticalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            groupedHistory.forEach { (header, items) ->
                item {
                    Text(
                        text = header,
                        style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                        modifier = Modifier.padding(vertical = 6.dp)
                    )
                }
                itemsIndexed(items) { index, item ->
                    HistoryCard(item)
                    Spacer(modifier = Modifier.height(6.dp))
                }
            }
        }
    }
}