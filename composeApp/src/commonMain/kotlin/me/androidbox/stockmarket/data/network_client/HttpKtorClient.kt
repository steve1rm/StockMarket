package me.androidbox.stockmarket.data.network_client

import co.touchlab.kermit.Logger
import io.ktor.client.*
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json

class HttpKtorClient {

    fun build(): HttpClient {
        return HttpClient(CIO) {
            install(ContentNegotiation) {
                json(
                    Json {
                        prettyPrint = true
                        ignoreUnknownKeys = true
                        coerceInputValues = true
                    }
                )
            }

            install(Logging) {
                this.logger = object : io.ktor.client.plugins.logging.Logger {
                    override fun log(message: String) {
                        Logger.d { message }
                    }

                }

              /*  if (BuildConfig.DEBUG) {
                    this.level = LogLevel.ALL
                } else {
                    this.level = LogLevel.NONE
                }*/
            }

            defaultRequest {
                contentType(ContentType.Application.Json)
                accept(ContentType.Application.Json)
            }
        }
    }
}