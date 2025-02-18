package org.example.project.data.mappers

import org.example.project.data.networkModels.PhotoNetworkModel
import org.example.project.domain.Photo

fun PhotoNetworkModel.toPhoto() = Photo(
    id = id,
    url = imageUrl
)