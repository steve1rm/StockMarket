package me.androidbox.stockmarket.data.remote_data_source

import io.ktor.client.*
import io.ktor.client.request.*
import me.androidbox.stockmarket.data.model.QuoteDto
import me.androidbox.stockmarket.data.model.SearchDto
import me.androidbox.stockmarket.data.model.SearchResultDto
import me.androidbox.stockmarket.data.model.SymbolDto
import me.androidbox.stockmarket.data.remote.Routes
import me.androidbox.stockmarket.data.utils.ErrorDto
import me.androidbox.stockmarket.data.utils.safeApiRequest
import me.androidbox.stockmarket.domain.CheckResult
import me.androidbox.stockmarket.domain.DataError

class StockRemoteDataSourceImp(
    private val httpClient: HttpClient
) {

    // https://finnhub.io/api/v1/stock/symbol?exchange=US&token=d4b6ljpr01qrv4asvurgd4b6ljpr01qrv4asvus0
    // https://finnhub.io/api/v1/stock/symbol?exchange=US&token=d4b6ljpr01qrv4asvurgd4b6ljpr01qrv4asvus0

    suspend fun fetchStockSymbols(): CheckResult<List<SymbolDto>, DataError.Network, ErrorDto> {
        val safeResult = safeApiRequest<List<SymbolDto>> {
            val response = httpClient
                .get(Routes.SYMBOL) {
                    url {
                        this.parameters.append("exchange", "US")
                        this.parameters.append("token", "d4b6ljpr01qrv4asvurgd4b6ljpr01qrv4asvus0") // TODO add to local.properties
                    }
                }

            response
        }

        return safeResult
    }

    suspend fun searchStock(): CheckResult<SearchResultDto, DataError.Network, ErrorDto> {
        val safeResult = safeApiRequest<SearchResultDto> {
            val response = httpClient
                .get(Routes.SEARCH) {
                    url {
                        this.parameters.append("exchange", "US")
                        this.parameters.append("q", "aapl")
                        this.parameters.append("token", "d4b6ljpr01qrv4asvurgd4b6ljpr01qrv4asvus0") // TODO add to local.properties
                    }
                }

            response
        }

        return safeResult
    }

    suspend fun fetchStockQuote(symbol: String): CheckResult<QuoteDto, DataError.Network, ErrorDto> {
        val safeResult = safeApiRequest<QuoteDto> {
            val response = httpClient
                .get(Routes.QUOTE) {
                    url {
                        this.parameters.append("symbol", symbol)
                        this.parameters.append("token", "d4b6ljpr01qrv4asvurgd4b6ljpr01qrv4asvus0") // TODO add to local.properties
                    }
                }
            response
        }
        return safeResult
    }
}