package org.example.project.di

import org.example.project.data.utils.HttpClientFactory
import org.example.project.data.network.KtorRemotePhotoDataSource
import org.example.project.data.network.RemotePhotoDataSource
import org.example.project.data.repository.PhotoRepositoryImpl
import org.example.project.domain.PhotoRepository
import org.example.project.ui.photolistscreen.PhotoListScreenViewModel
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.bind
import org.koin.dsl.module

expect val platformModule: Module

val sharedModule = module {
    single { HttpClientFactory.create(get()) }
    singleOf(::KtorRemotePhotoDataSource).bind<RemotePhotoDataSource>()
    singleOf(::PhotoRepositoryImpl).bind<PhotoRepository>()

    viewModelOf(::PhotoListScreenViewModel)
}