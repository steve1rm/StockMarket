package me.androidbox.stockmarket.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
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

            Box(modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize(),
                contentAlignment = Alignment.Center) {

                Text(
                    modifier = Modifier.align(Alignment.TopCenter),
                    text = "Stock Trading App",
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 28.sp,
                    lineHeight = 32.sp,
                    color = Color(0xFF2e3642)
                )

                if(stockState.isLoading) {
                    CircularProgressIndicator()
                }
                else {
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(horizontal = 16.dp),
                        state = rememberLazyListState(),
                        verticalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
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
            }
        }
    )
}