package com.lamz.core.domain.repository

import com.lamz.core.data.ResourceState
import com.lamz.core.domain.model.KumpulanDoaDoa
import kotlinx.coroutines.flow.Flow

interface IKumpulanDoaRepository {
    fun getAllDoa(): Flow<ResourceState<List<KumpulanDoaDoa>>>
    fun getAllFavorite(): Flow<List<KumpulanDoaDoa>>
    fun setFavoriteDoa(kumpulanDoaDoa: KumpulanDoaDoa, state: Boolean)
}