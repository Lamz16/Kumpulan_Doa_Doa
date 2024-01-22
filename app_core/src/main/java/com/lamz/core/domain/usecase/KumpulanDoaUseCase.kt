package com.lamz.core.domain.usecase

import com.lamz.core.data.ResourceState
import com.lamz.core.domain.model.KumpulanDoaDoa
import kotlinx.coroutines.flow.Flow

interface KumpulanDoaUseCase {
    fun getAllDoa(): Flow<ResourceState<List<KumpulanDoaDoa>>>
    fun getAllFavorite(): Flow<List<KumpulanDoaDoa>>
    fun setFavoriteDoa(kumpulanDoaDoa: KumpulanDoaDoa, state: Boolean)
}