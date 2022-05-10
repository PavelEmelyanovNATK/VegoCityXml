package com.emelyanov.vegocityxml.navigation.core

import com.emelyanov.vegocityxml.shared.domain.utils.BaseNavProvider
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CoreNavProvider
@Inject
constructor(

) : BaseNavProvider<CoreDestinations>()