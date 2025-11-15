package me.androidbox.stockmarket

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform