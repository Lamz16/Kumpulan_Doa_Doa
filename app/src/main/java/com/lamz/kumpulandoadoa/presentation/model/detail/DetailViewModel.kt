package com.lamz.kumpulandoadoa.presentation.model.detail

import androidx.lifecycle.ViewModel
import com.lamz.core.domain.model.KumpulanDoaDoa
import com.lamz.core.domain.usecase.KumpulanDoaUseCase

class DetailViewModel(private val kumpulanDoaUseCase: KumpulanDoaUseCase): ViewModel() {
    fun setFavoriteDoa(kumpulanDoaDoa : KumpulanDoaDoa, state: Boolean) = kumpulanDoaUseCase.setFavoriteDoa(kumpulanDoaDoa, state)
}