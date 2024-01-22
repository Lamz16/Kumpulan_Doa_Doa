package com.lamz.core.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@Parcelize
data class KumpulanDoaDoa(
    val id : String,
    val doa : String,
    val ayat : String,
    val latin : String,
    val artinya : String,
    val favorite : Boolean
):Parcelable