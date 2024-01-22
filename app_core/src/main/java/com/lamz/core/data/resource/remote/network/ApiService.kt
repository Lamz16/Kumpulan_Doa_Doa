package com.lamz.core.data.resource.remote.network

import com.lamz.core.data.resource.remote.response.ListItemKumpulanDoaResponse
import retrofit2.http.GET

interface ApiService {
    @GET("api")
    suspend fun getAllDoa(): List<ListItemKumpulanDoaResponse>
}