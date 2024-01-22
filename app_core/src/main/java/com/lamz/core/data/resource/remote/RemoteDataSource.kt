package com.lamz.core.data.resource.remote

import com.lamz.core.data.resource.remote.network.ApiResponse
import com.lamz.core.data.resource.remote.network.ApiService
import com.lamz.core.data.resource.remote.response.ListItemKumpulanDoaResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.HttpException

class RemoteDataSource(private val apiService: ApiService) {
    suspend fun getAllDoa(): Flow<ApiResponse<List<ListItemKumpulanDoaResponse>>> {
        return flow {
            try {
                val result = apiService.getAllDoa()
                if (result.isNotEmpty()){
                    emit(ApiResponse.Success(result))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e : HttpException){
                emit(ApiResponse.Error(e.toString()))
            } catch (e: Exception){
                emit(ApiResponse.Error(e.toString()))
            }
        }.flowOn(Dispatchers.IO)
    }
}