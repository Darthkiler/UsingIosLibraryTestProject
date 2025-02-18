package org.example.project.data.utils


import io.ktor.client.call.NoTransformationFoundException
import io.ktor.client.call.body
import io.ktor.client.network.sockets.SocketTimeoutException
import io.ktor.client.statement.HttpResponse

suspend inline fun <reified T> safeCall(
    execute: () -> HttpResponse
): Result<T> {
    val response = try {
        execute()
    } catch(e: SocketTimeoutException) {
        return Result.failure(e)
    }

    return responseToResult(response)
}

suspend inline fun <reified T> responseToResult(
    response: HttpResponse
): Result<T> {
    return when(response.status.value) {
        in 200..299 -> {
            try {
                Result.success(response.body<T>())
            } catch(e: NoTransformationFoundException) {
                Result.failure(e)
            }
        }
        else -> Result.failure(Exception(response.status.description))
    }
}