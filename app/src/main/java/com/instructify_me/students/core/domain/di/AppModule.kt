package com.instructify_me.students.core.domain.di

import android.content.Context
import com.instructify_me.students.core.domain.objects.LocalClient
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json


interface AppModule {
    val ktorClient: HttpClient
    val userClient: LocalClient
}

class AppModuleImpl(context: Context): AppModule {
    override val ktorClient: HttpClient by lazy {
        HttpClient {
            install(ContentNegotiation) {
                json()
            }
        }
    }
    override val userClient: LocalClient by lazy {
        LocalClient(context)
        
    }

}