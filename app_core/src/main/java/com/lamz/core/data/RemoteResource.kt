package com.lamz.core.data

import com.lamz.core.data.resource.remote.network.ApiResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

@Suppress("EmptyMethod")
abstract class RemoteResource<ResultType, RequestType> {
    private var result: Flow<ResourceState<ResultType>> = flow {
        emit(ResourceState.Loading())
        val dbSource = loadFromDB().first()
        if (shouldFetch(dbSource)){
            emit(ResourceState.Loading())
            when(val apiResponse = createCall().first()){
                is ApiResponse.Success -> {
                    saveCallResult(apiResponse.data)
                    emitAll(loadFromDB().map { ResourceState.Success(it) })
                }

                is ApiResponse.Empty -> {
                    emitAll(loadFromDB().map { ResourceState.Success(it) })
                }

                is ApiResponse.Error -> {
                    onFetchFailed()
                    emit(ResourceState.Error(apiResponse.errorMessage))
                }
            }
        }else{
            emitAll(loadFromDB().map { ResourceState.Success(it) })
        }
    }

    protected open fun onFetchFailed() {}

    protected abstract fun loadFromDB(): Flow<ResultType>

    protected abstract fun shouldFetch(data: ResultType?): Boolean

    protected abstract suspend fun createCall(): Flow<ApiResponse<RequestType>>

    protected abstract suspend fun saveCallResult(data: RequestType)

    fun asFlow(): Flow<ResourceState<ResultType>> = result

}