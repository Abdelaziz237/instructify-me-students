package com.instructify_me.students.core.domain.di

import io.ktor.client.HttpClient
import io.ktor.serialization.kotlinx.json.json


interface AppModule {
    val ktorClient: HttpClient
}

class AppModuleImpl: AppModule {
    override val ktorClient: HttpClient by lazy {
        HttpClient {
            install(io.ktor.client.plugins.contentnegotiation.ContentNegotiation) {
                json()
            }
        }
    }

}