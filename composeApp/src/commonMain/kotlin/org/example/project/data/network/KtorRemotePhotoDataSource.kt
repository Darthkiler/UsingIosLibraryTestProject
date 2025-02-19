package org.example.project.data.network

import io.ktor.client.HttpClient
import org.example.project.data.networkModels.PhotoNetworkModel
import kotlin.random.Random

class KtorRemotePhotoDataSource(
    private val httpClient: HttpClient
) : RemotePhotoDataSource {
    override suspend fun getPhotoList(): Result<List<PhotoNetworkModel>> {
        return Result.success(
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
//       return safeCall {
//            httpClient.get(
//                urlString = "http://0.0.0.0:8080/photoList"
//            )
//        }
    }
}