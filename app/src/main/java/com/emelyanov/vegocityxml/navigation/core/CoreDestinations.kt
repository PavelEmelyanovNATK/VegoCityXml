package com.emelyanov.vegocityxml.navigation.core

sealed class CoreDestinations {
    object Catalog : CoreDestinations()
    object Favorites : CoreDestinations()
    object Info : CoreDestinations()
    object Cart : CoreDestinations()
    object ShowDialog : CoreDestinations()
    object PopBack : CoreDestinations()
}