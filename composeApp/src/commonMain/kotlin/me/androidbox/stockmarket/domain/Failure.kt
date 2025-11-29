package me.androidbox.stockmarket.domain

import me.androidbox.stockmarket.data.utils.ErrorDto

data class Failure(val error: DataError.Network, val errorBody: ErrorDto? = null)