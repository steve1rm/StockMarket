package me.androidbox.stockmarket

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import me.androidbox.stockmarket.data.network_client.HttpKtorClient
import me.androidbox.stockmarket.data.remote_data_source.StockRemoteDataSourceImp
import me.androidbox.stockmarket.data.repository.StockRepositoryImp
import me.androidbox.stockmarket.presentation.StockScreen
import me.androidbox.stockmarket.presentation.StockViewModel
import me.androidbox.stockmarket.presentation.ViewModelFactory
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    MaterialTheme {
        val stockRemoteDataSourceImp = StockRemoteDataSourceImp( HttpKtorClient().build())
        val stockRepositoryImp = StockRepositoryImp(stockRemoteDataSourceImp)
        val stockViewModel = viewModel<StockViewModel>(
            factory = ViewModelFactory(stockRepositoryImp)
        )
        val stockState by stockViewModel.state.collectAsStateWithLifecycle()

        StockScreen(
            stockState = stockState
        )
    }
}