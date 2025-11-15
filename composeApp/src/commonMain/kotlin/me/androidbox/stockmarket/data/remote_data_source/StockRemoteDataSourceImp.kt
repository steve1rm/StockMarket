package me.androidbox.stockmarket.data.remote_data_source

import androidx.compose.ui.graphics.vector.PathNode
import io.ktor.client.*
import io.ktor.client.request.get
import io.ktor.http.headers
import me.androidbox.stockmarket.data.model.QuoteDto
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

    suspend fun fetchStockSymbols(): CheckResult<SymbolDto, DataError.Network, ErrorDto> {
        val safeResult = safeApiRequest<SymbolDto> {
            val response = httpClient
                .get(Routes.SYMBOL) {
                    url {
                        this.parameters.append("exchange", "US")
                        this.parameters.append("token", "d4b6ljpr01qrv4asvurgd4b6ljpr01qrv4asvus0") // TODO add to local.properties
                    }
                /*    headers {
                        this.append("X-Finnhub-Token", "d4b6ljpr01qrv4asvurgd4b6ljpr01qrv4asvus0") // TODO add to local.properties
                    }*/
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
                 /*   headers {
                        this.append("token", "d4b6ljpr01qrv4asvurgd4b6ljpr01qrv4asvus0") // TODO add to local.properties
                    }*/
                }
            response
        }
        return safeResult
    }
}