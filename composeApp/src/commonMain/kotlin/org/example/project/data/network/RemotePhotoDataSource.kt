package org.example.project.data.network

import org.example.project.data.networkModels.PhotoNetworkModel

interface RemotePhotoDataSource {
    suspend fun getPhotoList(): Result<List<PhotoNetworkModel>>
}