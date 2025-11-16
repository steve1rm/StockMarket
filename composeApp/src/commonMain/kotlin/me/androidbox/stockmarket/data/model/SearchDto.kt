package me.androidbox.stockmarket.data.model

import kotlinx.serialization.Serializable

@Serializable
data class SearchDto(
    val description: String,
    val displaySymbol: String,
    val symbol: String,
    val type: String
)

@Serializable
data class SearchResultDto(
    val result: List<SearchDto>
)
