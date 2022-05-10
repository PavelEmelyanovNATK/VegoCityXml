package com.emelyanov.vegocityxml.navigation.core

import androidx.navigation.NavController
import com.emelyanov.vegocityxml.R
import kotlinx.coroutines.CoroutineScope

fun launchCoreNavHost(
    scope: CoroutineScope,
    coreNavProvider: CoreNavProvider,
    coreNavController: NavController
) {
    coreNavProvider.observeDestination(scope) {
        when(it) {
            is CoreDestinations.PopBack -> coreNavController.popBackStack()
            is CoreDestinations.Catalog -> coreNavController.navigate(R.id.popupToCatalog)
            is CoreDestinations.Favorites -> coreNavController.navigate(R.id.popupToFavorites)
            is CoreDestinations.Info -> coreNavController.navigate(R.id.popupToInfo)
            is CoreDestinations.Cart -> coreNavController.navigate(R.id.popupToCart)
            is CoreDestinations.ShowDialog -> coreNavController.navigate(R.id.showProductDetails)
            else -> {}
        }
    }
}