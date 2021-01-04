package io.viktoriia.architecture.base.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.navigation.NavDirections
import io.viktoriia.architecture.base.livedata.SingleLiveEvent

open class StatefulAndroidViewModel<T>(
    application: Application
) : AndroidViewModel(application) {

    protected val _state = MutableLiveData<State<T>>().apply {
        value = defaultState()
    }

    /***
     * VM state, used by VM during methods execution to provide results to subscribers.
     */
    fun state(): LiveData<State<T>> = _state

    protected val _viewEvents = SingleLiveEvent<ViewEvent>()

    /***
     * UI connected VM events that appear once without duplication on new subscriptions.
     */
    fun viewEvents(): LiveData<ViewEvent> = _viewEvents

    /***
     * Override in descendants to obtain default state as non [State.Empty] default value.
     */
    protected fun defaultState(): State<T> {
        return State.Empty()
    }

    sealed class State<T> {
        data class Success<T>(val data: T) : State<T>()
        class Empty<T> : State<T>()
        data class Error<T>(val error: String) : State<T>()
        data class NetworkError<T>(val error: String) : State<T>()
        class NoConnectionError<T> : State<T>()
    }

    sealed class ViewEvent {
        data class Progress(val showProgress: Boolean) : ViewEvent()
        data class NavigateTo(val navDirection: NavDirections) : ViewEvent()
        data class Message(val message: String) : ViewEvent()
        class Action(vararg val args: Any) : ViewEvent()
    }
}