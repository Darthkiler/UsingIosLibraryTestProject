package org.example.project.domain

interface PhotoRepository {
    suspend fun getPhotoList(): Result<List<Photo>>
}