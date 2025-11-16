package me.androidbox.stockmarket.data.repository

import me.androidbox.stockmarket.data.model.QuoteDto
import me.androidbox.stockmarket.data.model.SearchDto
import me.androidbox.stockmarket.data.model.SearchResultDto
import me.androidbox.stockmarket.data.model.SymbolDto
import me.androidbox.stockmarket.data.remote_data_source.StockRemoteDataSourceImp
import me.androidbox.stockmarket.data.utils.ErrorDto
import me.androidbox.stockmarket.domain.CheckResult
import me.androidbox.stockmarket.domain.DataError

class StockRepositoryImp(
    private val stockRemoteDataSourceImp: StockRemoteDataSourceImp
) {
    suspend fun fetchStockSymbols(): CheckResult<List<SymbolDto>, DataError.Network, ErrorDto> {
        return stockRemoteDataSourceImp.fetchStockSymbols()
    }

    suspend fun searchStock(): CheckResult<SearchResultDto, DataError.Network, ErrorDto> {
        return stockRemoteDataSourceImp.searchStock()
    }

    suspend fun fetchStockQuote(symbol: String): CheckResult<QuoteDto, DataError, ErrorDto> {
        return stockRemoteDataSourceImp.fetchStockQuote(symbol)
    }
}
