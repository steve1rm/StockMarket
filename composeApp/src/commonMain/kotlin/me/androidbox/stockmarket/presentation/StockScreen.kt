package me.androidbox.stockmarket.presentation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

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
                        text = "Stock Trading App",
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 28.sp,
                        lineHeight = 32.sp,
                        color = Color(0xFF2e3642)
                    )
                }

                items(
                    items = stockState.stockItems,
                    key = { stockItem ->
                        stockItem.symbol
                    },
                    itemContent = { stockItem ->
                        StockItemView(
                            symbol = stockItem.symbol,
                            currentPrice = stockItem.quote.current,
                            currentDate = stockItem.description,
                            currency = "USD"
                        )
                    }
                )
            }
        }
    )
}