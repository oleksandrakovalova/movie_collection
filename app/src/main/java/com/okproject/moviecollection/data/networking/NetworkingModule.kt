package com.okproject.moviecollection.data.networking

import com.okproject.moviecollection.data.networking.client.APIClientFactory
import org.koin.dsl.module

val networking = module {
    single<MovieApi> { APIClientFactory.createClient().create(MovieApi::class.java) }
}