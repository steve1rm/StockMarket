package me.androidbox.stockmarket.domain

data class ErrorModel(
    val status: String,
    val type: String,
    val message: String
)