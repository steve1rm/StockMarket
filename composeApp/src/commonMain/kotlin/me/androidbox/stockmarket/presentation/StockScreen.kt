package me.androidbox.stockmarket.presentation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun StockScreen(
    stockState: StockState
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        content = { paddingValues ->
            LazyColumn(
                modifier = Modifier.padding(paddingValues).padding(horizontal = 16.dp)
            ) {
                item {
                    Text(
                        text = "Stock Trading App in real time"
                    )
                }
            }
        }
    )
}