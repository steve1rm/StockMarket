package me.androidbox.stockmarket.data.repository

import arrow.core.Either
import me.androidbox.stockmarket.data.model.QuoteDto
import me.androidbox.stockmarket.data.model.SearchResultDto
import me.androidbox.stockmarket.data.model.SymbolDto
import me.androidbox.stockmarket.data.remote_data_source.StockRemoteDataSourceImp
import me.androidbox.stockmarket.domain.DataError

class StockRepositoryImp(
    private val stockRemoteDataSourceImp: StockRemoteDataSourceImp
) {
    suspend fun fetchStockSymbols(): Either<DataError.Network, List<SymbolDto>> {
        return stockRemoteDataSourceImp.fetchStockSymbols()
    }

    suspend fun searchStock(symbol: String): Either<DataError.Network, SearchResultDto> {
        return stockRemoteDataSourceImp.searchStock(symbol)
    }

    suspend fun fetchStockQuote(symbol: String): Either<DataError, QuoteDto> {
        return stockRemoteDataSourceImp.fetchStockQuote(symbol)
    }
}
