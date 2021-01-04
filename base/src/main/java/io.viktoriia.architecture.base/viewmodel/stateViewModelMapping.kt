package io.viktoriia.architecture.base.viewmodel

import io.viktoriia.architecture.base.result.AppResult

inline fun <T : Any, R> AppResult<T>.mapToState(transformation: (input: T) -> R): StatefulAndroidViewModel.State<R> {
    return when (this) {
        is AppResult.Success -> StatefulAndroidViewModel.State.Success(
            transformation(this.data)
        )
        is AppResult.Error -> StatefulAndroidViewModel.State.Error(
            this.message
                ?: "Unknown error"
        )
    }
}