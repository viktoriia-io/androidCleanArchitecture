package io.viktoriia.architecture.app.di

import io.viktoriia.architecture.app.ui.viewmodel.TestViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val koinModules = listOf(
    module {
        viewModel {
            TestViewModel(
                application = get()
            )
        }
    }
)