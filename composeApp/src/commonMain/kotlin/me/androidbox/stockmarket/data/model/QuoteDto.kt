package me.androidbox.stockmarket.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class QuoteDto(
    @SerialName("c")
    val currentPrice: Float,
    @SerialName("d")
    val change: Float,
    @SerialName("dp")
    val percentChange: Float,
    @SerialName("h")
    val highPrice: Float,
    @SerialName("i")
    val lowPrice: Float,
    @SerialName("o")
    val openPrice: Float,
    @SerialName("pc")
    val previousPrice: Float
)
