package io.viktoriia.architecture.app.ui.viewmodel

import android.app.Application
import io.viktoriia.architecture.base.result.AppResult
import io.viktoriia.architecture.base.viewmodel.StatefulAndroidViewModel
import io.viktoriia.architecture.base.viewmodel.mapToState

class TestViewModel(application: Application) :
    StatefulAndroidViewModel<String>(application) {

    fun loadTestData() {
        _viewEvents.postValue(StatefulAndroidViewModel.ViewEvent.Progress(true))
        Thread.sleep(2000)
        val result = AppResult.Success("Success")
        _state.postValue(result.mapToState { it })
        _viewEvents.postValue(StatefulAndroidViewModel.ViewEvent.Progress(false))
    }
}