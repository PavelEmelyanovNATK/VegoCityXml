package com.emelyanov.vegocityxml.shared.domain.utils

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlin.coroutines.CoroutineContext

abstract class BaseNavProvider<D> {
    private val _destinationFlow: MutableStateFlow<D?> = MutableStateFlow(null)

    fun observeDestination(scope: CoroutineScope, observer: (D?) -> Unit){
        _destinationFlow.onEach {
            observer(it)
            _destinationFlow.tryEmit(null)
        }.launchIn(scope)
    }

    fun navigateTo(destination: D?) = _destinationFlow.tryEmit(destination)
}