package me.androidbox.stockmarket.presentation

data class StockState(
    val stockItems: List<StockItem> = emptyList()
)

data class StockItem(
    val description: String = "",
    val symbol: String = "",
    val quote: StockQuote = StockQuote()
)

data class StockQuote(
    val current: Float = 0.0F,
    val lowest: Float = 0.0F,
    val highest: Float = 0.0F,
    val change: Float = 0.0f
)