package org.example.project.data.repository

import org.example.project.data.mappers.toPhoto
import org.example.project.data.network.RemotePhotoDataSource
import org.example.project.domain.Photo
import org.example.project.domain.PhotoRepository

class PhotoRepositoryImpl(
    private val remotePhotoDataSource: RemotePhotoDataSource
): PhotoRepository {
    override suspend fun getPhotoList(): Result<List<Photo>> {
        return remotePhotoDataSource.getPhotoList().map {
            it.map { it.toPhoto() }
        }
    }
}