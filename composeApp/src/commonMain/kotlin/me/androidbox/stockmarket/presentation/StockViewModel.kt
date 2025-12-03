package me.androidbox.stockmarket.presentation

import arrow.core.Either
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.touchlab.kermit.Logger
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import me.androidbox.stockmarket.data.repository.StockRepositoryImp

class StockViewModel(
    private val stockRepositoryImp: StockRepositoryImp
) : ViewModel() {
    private var hasLoaded = false

    private val stockSymbols = listOf(
        "GOOG", "NVDA", "NFLX", "TSLA"
    )

    private val _state = MutableStateFlow(StockState())
    val state = _state.asStateFlow()
        .onStart {
            if(!hasLoaded) {
                loadAllStockItems()
                hasLoaded = true
            }
        }
        .stateIn(
            scope = viewModelScope,
            initialValue = StockState(),
            started = SharingStarted.WhileSubscribed(5_000)
        )

    private fun loadAllStockItems() {
        viewModelScope.launch {
            _state.update {
                it.copy(
                    isLoading = true
                )
            }
            val stockItems = fetchStockSymbols(stockSymbols)

            val allStockItems = stockItems
                .filterNotNull()
                .map { stockItem ->
                    async {
                        fetchStockQuote(stockItem.symbol)?.let { stockQuote ->
                            stockItem.copy(quote = stockQuote)
                        }
                    }
                }

            _state.update {
                StockState(
                    stockItems = allStockItems.awaitAll()
                        .filterNotNull(),
                    isLoading = false
                )
            }
        }
    }

    private suspend fun fetchStockQuote(symbol: String): StockQuote? {
        return when(val quote = stockRepositoryImp.fetchStockQuote(symbol)) {
            is Either.Left -> {
                Logger.e {
                    "Error ${quote.value}"
                }
                null
            }

            is Either.Right -> {
                Logger.d {
                    "${quote.value.currentPrice}"
                }

                StockQuote(
                    current = quote.value.currentPrice,
                    lowest = quote.value.lowPrice,
                    highest = quote.value.highPrice,
                    change = quote.value.percentChange
                )
            }
        }
    }

    private suspend fun fetchStockSymbols(symbols: List<String>): List<StockItem?> {
        val listOfStock = symbols.map { symbol ->
            viewModelScope.async {
                when (val stock = stockRepositoryImp.searchStock(symbol)) {
                    is Either.Right -> {
                        /** There could be more than one stock item returned for each symbol,
                            so lets take the first one */
                        val searchDto = stock.value.result.first()

                        StockItem(
                            description = searchDto.description,
                            symbol = searchDto.symbol)
                    }

                    is Either.Left -> {
                        Logger.e {
                            stock.value.name
                        }
                        null
                    }
                }
            }
        }

        return listOfStock.awaitAll()
    }
}
