package org.example.project

import com.example.networkmodels.PhotoNetworkModel
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.Application
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import io.ktor.server.response.respond
import io.ktor.server.response.respondText
import io.ktor.server.routing.get
import io.ktor.server.routing.routing
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.Json.Default.encodeToString
import kotlin.random.Random

fun main() {
    embeddedServer(Netty, port = 8080, host = "0.0.0.0", module = Application::module)
        .start(wait = true)
}

fun Application.module() {
    routing {
        get("/") {
            call.respondText("Hello world")
        }
        get("/photoList") {
            call.respond(
                status = HttpStatusCode.OK,
                message = Json.encodeToString(
                    (0..100).shuffled().map { id ->
                        PhotoNetworkModel(
                            id = id,
                            imageUrl = "https://picsum.photos/id/${id}/200/300",
                            imageDescription = "Random description from image with id = $id".takeIf {
                                Random.nextBoolean()
                            }
                        )
                    }
                )
            )
        }
    }
}