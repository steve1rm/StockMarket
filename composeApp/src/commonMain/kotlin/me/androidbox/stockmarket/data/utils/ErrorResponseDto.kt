package me.androidbox.stockmarket.data.utils

import kotlinx.serialization.Serializable

@Serializable
data class ErrorResponseDto(
    val errors: List<ErrorDto>
)