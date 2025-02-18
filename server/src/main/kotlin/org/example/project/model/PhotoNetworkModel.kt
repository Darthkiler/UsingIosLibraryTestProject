package org.example.project.model

import kotlinx.serialization.Serializable

@Serializable
data class PhotoNetworkModel(
    val id: Int,
    val imageUrl: String,
    val imageDescription: String?,
)