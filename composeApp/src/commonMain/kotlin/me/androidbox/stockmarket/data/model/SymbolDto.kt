package me.androidbox.stockmarket.data.model

import kotlinx.serialization.Serializable

@Serializable
data class SymbolDto(
    val description: String,
    val displaySymbol: String,
    val symbol: String,
    val type: String
)
