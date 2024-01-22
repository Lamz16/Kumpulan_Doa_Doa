package com.lamz.core.data.resource.local.entity

import android.annotation.SuppressLint
import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "kumpulanDoa")
data class KumpulanDoaEntity (
    @SuppressLint("KotlinNullnessAnnotation")
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    var id : String,

    @ColumnInfo(name = "doa")
    var doa : String,

    @ColumnInfo(name = "ayat")
    var ayat : String,

    @ColumnInfo(name = "latin")
    var latin : String,

    @ColumnInfo(name = "artinya")
    var artinya : String,

    @ColumnInfo(name = "isFavorite")
    var favorite : Boolean = false
)