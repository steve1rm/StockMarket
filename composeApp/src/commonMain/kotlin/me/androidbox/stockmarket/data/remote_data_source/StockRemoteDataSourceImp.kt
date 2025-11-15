package me.androidbox.stockmarket.data.remote_data_source

import io.ktor.client.HttpClient

class StockRemoteDataSourceImp(
    private val httpClient: HttpClient
) {
    suspend fun fetchStock() {

    }
}