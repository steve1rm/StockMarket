package me.androidbox.stockmarket.data.utils

import co.touchlab.kermit.Logger
import io.ktor.client.call.body
import io.ktor.client.statement.HttpResponse
import io.ktor.util.network.UnresolvedAddressException
import kotlinx.serialization.SerializationException
import me.androidbox.stockmarket.domain.DataError
import me.androidbox.stockmarket.domain.CheckResult
import kotlin.coroutines.cancellation.CancellationException

suspend inline fun <reified D> safeApiRequest(block: () -> HttpResponse): CheckResult<D, DataError.Network, ErrorDto> {
    val httpResponse = try {
        block()
    }
    catch(exception: UnresolvedAddressException) {
        exception.printStackTrace()

        return CheckResult.Failure(DataError.Network.NO_INTERNET)
    }
    catch(exception: SerializationException) {
        exception.printStackTrace()

        return CheckResult.Failure(DataError.Network.SERIALIZATION)
    }
    catch (exception: Exception) {
        exception.printStackTrace()

        if (exception is CancellationException) {
            Logger.e {
                "$exception"
            }
            throw exception
        }
        return CheckResult.Failure(DataError.Network.UNKNOWN)
    }

    return responseToResult(httpResponse)
}


suspend inline fun <reified D> responseToResult(response: HttpResponse): CheckResult<D, DataError.Network, ErrorDto> {
    return when(response.status.value) {
        in 200..299 -> {
            CheckResult.Success(response.body<D>())
        }
        401 -> {
            CheckResult.Failure(DataError.Network.UNAUTHORIZED, response.body<ErrorDto>())
        }
        408 -> {
            CheckResult.Failure(DataError.Network.REQUEST_TIMEOUT, response.body<ErrorDto>())
        }
        409 -> {
            CheckResult.Failure(DataError.Network.CONFLICT, response.body<ErrorDto>())
        }
        413 -> {
            CheckResult.Failure(DataError.Network.PAYLOAD_TOO_LARGE, response.body<ErrorDto>())
        }
        429 -> {
            val error = ErrorDto(
                status = response.status.value.toString(),
                type = response.status.description,
            )
            CheckResult.Failure(DataError.Network.TOO_MANY_REQUESTS, error)
        }
        in 500..599 -> {
            CheckResult.Failure(DataError.Network.SERVER_ERROR, response.body<ErrorDto>())
        }
        else -> {
            CheckResult.Failure(DataError.Network.UNKNOWN, response.body<ErrorDto>())
        }
    }
}