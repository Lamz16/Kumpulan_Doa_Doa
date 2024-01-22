package com.lamz.bookmark

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.lamz.core.domain.model.KumpulanDoaDoa
import com.lamz.core.domain.usecase.KumpulanDoaUseCase

class BookmarkViewModel(private val kumpulanDoaUseCase: KumpulanDoaUseCase) : ViewModel() {
    val favoriteDoa = kumpulanDoaUseCase.getAllFavorite().asLiveData()
    fun setFavoriteDoa(kumpulanDoaDoa : KumpulanDoaDoa, state: Boolean) = kumpulanDoaUseCase.setFavoriteDoa(kumpulanDoaDoa, state)
}