package me.androidbox.stockmarket.data.repository

import me.androidbox.stockmarket.data.remote_data_source.StockRemoteDataSourceImp

class StockRepositoryImp(
    private val stockRemoteDataSourceImp: StockRemoteDataSourceImp
) {
    suspend fun fetchStock() {

    }
}