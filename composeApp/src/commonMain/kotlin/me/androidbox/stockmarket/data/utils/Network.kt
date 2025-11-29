package me.androidbox.stockmarket.data.utils

import arrow.core.Either
import co.touchlab.kermit.Logger
import io.ktor.client.call.*
import io.ktor.client.statement.*
import io.ktor.util.network.*
import kotlinx.serialization.SerializationException
import me.androidbox.stockmarket.domain.DataError
import kotlin.coroutines.cancellation.CancellationException

suspend inline fun <reified D> safeApiRequest(block: () -> HttpResponse): Either<DataError.Network, D> {
    val httpResponse = try {
        block()
    }
    catch(exception: UnresolvedAddressException) {
        exception.printStackTrace()

        return Either.Left(DataError.Network.NO_INTERNET)
    }
    catch(exception: SerializationException) {
        exception.printStackTrace()

        return Either.Left(DataError.Network.SERIALIZATION)
    }
    catch (exception: Exception) {
        exception.printStackTrace()

        if (exception is CancellationException) {
            Logger.e {
                "$exception"
            }
            throw exception
        }
        return Either.Left(DataError.Network.UNKNOWN)
    }

    return responseToResult(httpResponse)
}


suspend inline fun <reified D> responseToResult(response: HttpResponse): Either<DataError.Network, D> {
    return when(response.status.value) {
        in 200..299 -> {
            Either.Right(response.body<D>())
        }
        401 -> {
            Either.Left(DataError.Network.UNAUTHORIZED)
        }
        408 -> {
            Either.Left(DataError.Network.REQUEST_TIMEOUT)
        }
        409 -> {
            Either.Left(DataError.Network.CONFLICT)
        }
        413 -> {
            Either.Left(DataError.Network.PAYLOAD_TOO_LARGE)
        }
        429 -> {
            val error = ErrorDto(
                status = response.status.value.toString(),
                type = response.status.description,
            )
            Either.Left(DataError.Network.TOO_MANY_REQUESTS)
        }
        in 500..599 -> {
            Either.Left(DataError.Network.SERVER_ERROR)
        }
        else -> {
            Either.Left(DataError.Network.UNKNOWN)
        }
    }
}