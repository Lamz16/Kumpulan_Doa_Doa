package com.lamz.bookmark

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val bookmarkModule = module {
    viewModel{ BookmarkViewModel(get()) }
}