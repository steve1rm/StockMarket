package me.androidbox.stockmarket.data.repository

import me.androidbox.stockmarket.data.model.QuoteDto
import me.androidbox.stockmarket.data.model.SymbolDto
import me.androidbox.stockmarket.data.remote_data_source.StockRemoteDataSourceImp
import me.androidbox.stockmarket.data.utils.ErrorDto
import me.androidbox.stockmarket.domain.CheckResult
import me.androidbox.stockmarket.domain.DataError

class StockRepositoryImp(
    private val stockRemoteDataSourceImp: StockRemoteDataSourceImp
) {
    suspend fun fetchStockSymbols(): CheckResult<SymbolDto, DataError.Network, ErrorDto> {
        return stockRemoteDataSourceImp.fetchStockSymbols()
    }

    suspend fun fetchStockQuote(symbol: String): CheckResult<QuoteDto, DataError, ErrorDto> {
        return stockRemoteDataSourceImp.fetchStockQuote(symbol)
    }
}
