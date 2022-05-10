package com.emelyanov.vegocityxml.modules.catalog.domain

import androidx.lifecycle.ViewModel
import com.emelyanov.vegocityxml.modules.catalog.domain.usecases.OpenProductDetailsUseCase
import dagger.hilt.android.internal.lifecycle.HiltViewModelMap
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CatalogViewModel
@Inject
constructor(
    private val openProductDetails: OpenProductDetailsUseCase
) : ViewModel() {
    fun productClick() = openProductDetails()
}