package org.example.project.data.networkModels

import kotlinx.serialization.Serializable

@Serializable
data class PhotoNetworkModel(
    val id: Int,
    val imageUrl: String,
    val imageDescription: String?,
)