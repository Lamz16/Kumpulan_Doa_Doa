package com.lamz.core.data.resource.local

import com.lamz.core.data.resource.local.entity.KumpulanDoaEntity
import com.lamz.core.data.resource.local.room.KumpulanDoaDao
import kotlinx.coroutines.flow.Flow

class LocalDataSource(private val kumpulanDoaDao: KumpulanDoaDao) {

    fun getAllDoa(): Flow<List<KumpulanDoaEntity>> = kumpulanDoaDao.getAllDoa()

    fun getAllFavorite(): Flow<List<KumpulanDoaEntity>> = kumpulanDoaDao.getAllFavorite()

    suspend fun insertDoa(doaList : List<KumpulanDoaEntity>) = kumpulanDoaDao.insertDoa(doaList)

    fun setFavorite(doa : KumpulanDoaEntity, newState : Boolean){
        doa.favorite = newState
        kumpulanDoaDao.updateFavoriteDoa(doa)
    }

    fun deleteAllDoa() = kumpulanDoaDao.delete()

}