package com.lamz.core.data.resource.local.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.PrimaryKey
import androidx.room.Query
import androidx.room.Update
import com.lamz.core.data.resource.local.entity.KumpulanDoaEntity
import kotlinx.coroutines.flow.Flow


@Dao
interface KumpulanDoaDao {
    @Query("SELECT * from kumpulanDoa")
    fun getAllDoa(): Flow<List<KumpulanDoaEntity>>

    @Query("SELECT * from kumpulanDoa where isFavorite = 1")
    fun getAllFavorite(): Flow<List<KumpulanDoaEntity>>

    @PrimaryKey(autoGenerate = false)
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDoa(doa : List<KumpulanDoaEntity>)

    @Update
    fun updateFavoriteDoa(doa : KumpulanDoaEntity)

    @Query("DELETE FROM kumpulanDoa WHERE isFavorite = 0")
    fun delete()
}