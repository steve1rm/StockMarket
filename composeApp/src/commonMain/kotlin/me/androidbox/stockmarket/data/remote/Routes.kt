package me.androidbox.stockmarket.data.remote

// d4b6ljpr01qrv4asvurgd4b6ljpr01qrv4asvus0

// Stock Symbol

// Quote

object Routes {
    private const val BASE_URL = "https://finnhub.io/api/v1"
    const val SYMBOL = "$BASE_URL/stock/symbol"
    const val QUOTE = "$BASE_URL/quote/"
    const val SEARCH = "$BASE_URL/search"
}