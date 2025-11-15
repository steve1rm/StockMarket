package me.androidbox.stockmarket.data.utils

import kotlinx.serialization.Serializable

@Serializable
data class ErrorDto(
    val status: String = "",
    val type: String = "",
    val message: String = ""
)