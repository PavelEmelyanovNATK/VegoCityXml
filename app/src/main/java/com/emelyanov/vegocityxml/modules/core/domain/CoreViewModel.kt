package com.emelyanov.vegocityxml.modules.core.domain

import androidx.lifecycle.ViewModel
import com.emelyanov.vegocityxml.modules.core.domain.usecases.NavigateToCartUseCase
import com.emelyanov.vegocityxml.modules.core.domain.usecases.NavigateToCatalogUseCase
import com.emelyanov.vegocityxml.modules.core.domain.usecases.NavigateToFavoritesUseCase
import com.emelyanov.vegocityxml.modules.core.domain.usecases.NavigateToInfoUseCase
import com.emelyanov.vegocityxml.navigation.core.CoreNavProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CoreViewModel
@Inject
constructor(
    val coreNavProvider: CoreNavProvider,
    private val navigateToCatalog: NavigateToCatalogUseCase,
    private val navigateToFavorites: NavigateToFavoritesUseCase,
    private val navigateToInfo: NavigateToInfoUseCase,
    private val navigateToCart: NavigateToCartUseCase
) : ViewModel() {
    fun catalogClick() = navigateToCatalog()
    fun favoritesClick() = navigateToFavorites()
    fun infoClick() = navigateToInfo()
    fun cartClick() = navigateToCart()
}