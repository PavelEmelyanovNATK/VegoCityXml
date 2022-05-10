package com.emelyanov.vegocityxml.modules.catalog.domain.usecases

import com.emelyanov.vegocityxml.navigation.core.CoreDestinations
import com.emelyanov.vegocityxml.navigation.core.CoreNavProvider
import javax.inject.Inject


class OpenProductDetailsUseCase
@Inject
constructor(
    private val coreNavProvider: CoreNavProvider
) {
    operator fun invoke()
    = coreNavProvider.navigateTo(CoreDestinations.ShowDialog)
}