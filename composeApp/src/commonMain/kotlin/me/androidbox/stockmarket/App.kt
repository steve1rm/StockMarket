package me.androidbox.stockmarket

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import co.touchlab.kermit.Logger
import io.ktor.client.HttpClient
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.cio.CIO
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import me.androidbox.stockmarket.data.network_client.HttpKtorClient
import me.androidbox.stockmarket.data.remote_data_source.StockRemoteDataSourceImp
import me.androidbox.stockmarket.data.repository.StockRepositoryImp
import me.androidbox.stockmarket.domain.CheckResult
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    MaterialTheme {
        var showContent by remember { mutableStateOf(false) }
        Column(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.primaryContainer)
                .safeContentPadding()
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            val stockRemoteDataSourceImp = StockRemoteDataSourceImp( HttpKtorClient().build())
            val stockRepositoryImp = StockRepositoryImp(stockRemoteDataSourceImp)

            runBlocking {
                val stockSymbols = stockRepositoryImp.fetchStockQuote("AAPL")

                when(stockSymbols) {
                    is CheckResult.Failure -> {
                        Logger.d {
                            "${stockSymbols.responseError?.message}"
                        }
                    }
                    is CheckResult.Success -> {
                        Logger.d {
                            "${stockSymbols.data}"
                        }

                    }
                }
            }
        }
    }
}