package com.lamz.kumpulandoadoa.di

import com.lamz.core.domain.usecase.AllKumpulanDoaInteractor
import com.lamz.core.domain.usecase.KumpulanDoaUseCase
import com.lamz.kumpulandoadoa.presentation.model.detail.DetailViewModel
import com.lamz.kumpulandoadoa.presentation.model.home.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory<KumpulanDoaUseCase> { AllKumpulanDoaInteractor(get()) }
}

val viewModelModule = module {
    viewModel { HomeViewModel(get()) }
    viewModel { DetailViewModel(get()) }
}
