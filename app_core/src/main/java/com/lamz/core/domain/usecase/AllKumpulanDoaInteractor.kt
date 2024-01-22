package com.lamz.core.domain.usecase

import com.lamz.core.domain.model.KumpulanDoaDoa
import com.lamz.core.domain.repository.IKumpulanDoaRepository

class AllKumpulanDoaInteractor(private val doaRepository : IKumpulanDoaRepository): KumpulanDoaUseCase {
    override fun getAllDoa() = doaRepository.getAllDoa()

    override fun getAllFavorite() = doaRepository.getAllFavorite()

    override fun setFavoriteDoa(kumpulanDoaDoa: KumpulanDoaDoa, state: Boolean)  = doaRepository.setFavoriteDoa(kumpulanDoaDoa,state)
}