package com.lamz.core.data.resource.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.lamz.core.data.resource.local.entity.KumpulanDoaEntity


@Database(entities = [KumpulanDoaEntity::class], version = 1, exportSchema = false)
abstract class DoaDatabase : RoomDatabase() {
    abstract fun doaDao(): KumpulanDoaDao
}