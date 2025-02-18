package com.example.networkmodels

import kotlinx.serialization.Serializable

@Serializable
data class PhotoNetworkModel(
    val id: Int,
    val imageUrl: String,
    val imageDescription: String?,
)