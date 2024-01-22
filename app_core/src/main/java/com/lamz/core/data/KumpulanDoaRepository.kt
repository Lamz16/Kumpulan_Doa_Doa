package com.lamz.core.data

import com.lamz.core.data.resource.local.LocalDataSource
import com.lamz.core.data.resource.remote.RemoteDataSource
import com.lamz.core.data.resource.remote.network.ApiResponse
import com.lamz.core.data.resource.remote.response.ListItemKumpulanDoaResponse
import com.lamz.core.domain.model.KumpulanDoaDoa
import com.lamz.core.domain.repository.IKumpulanDoaRepository
import com.lamz.core.utils.AppExecutors
import com.lamz.core.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class KumpulanDoaRepository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : IKumpulanDoaRepository {
    override fun getAllDoa(): Flow<ResourceState<List<KumpulanDoaDoa>>> =
        object : RemoteResource<List<KumpulanDoaDoa>, List<ListItemKumpulanDoaResponse>>() {
            override fun loadFromDB(): Flow<List<KumpulanDoaDoa>> {

                return localDataSource.getAllDoa().map {
                    DataMapper.mapEntitiesToDomain(it)
                }
            }

            override fun shouldFetch(data: List<KumpulanDoaDoa>?): Boolean {
                return !data!!.isNotEmpty()
            }

            override suspend fun createCall(): Flow<ApiResponse<List<ListItemKumpulanDoaResponse>>> =
                remoteDataSource.getAllDoa()

            override suspend fun saveCallResult(data: List<ListItemKumpulanDoaResponse>) {
                val doaList = DataMapper.mapResponsesToEntities(data)
                localDataSource.insertDoa(doaList)
            }
        }.asFlow()

    override fun getAllFavorite(): Flow<List<KumpulanDoaDoa>> {
        return localDataSource.getAllFavorite().map {
            DataMapper.mapEntitiesToDomain(it)
        }
    }

    override fun setFavoriteDoa(kumpulanDoaDoa: KumpulanDoaDoa, state: Boolean) {
        val doaEntity = DataMapper.mapDomainToEntity(kumpulanDoaDoa)
        appExecutors.diskIO().execute { localDataSource.setFavorite(doaEntity, state) }
    }
}