package com.emelyanov.vegocityxml.modules.core.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavOptionsBuilder
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import com.emelyanov.vegocityxml.R
import com.emelyanov.vegocityxml.databinding.ActivityMainBinding
import com.emelyanov.vegocityxml.modules.core.domain.CoreViewModel
import com.emelyanov.vegocityxml.modules.core.presentation.components.VegoBottomBarTabs
import com.emelyanov.vegocityxml.navigation.core.launchCoreNavHost
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val coreViewModel: CoreViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        with(binding) {
            setContentView(root)

            val navController = findNavController(R.id.mainNavHost)
            mainBottomBar.setupWithNavController(navController)

            launchCoreNavHost(
                scope = lifecycleScope,
                coreNavProvider = coreViewModel.coreNavProvider,
                coreNavController = navController
            )

            mainBottomBar.setOnClickListener {
                when(it) {
                    VegoBottomBarTabs.Catalog -> {
                        coreViewModel.catalogClick()
                    }
                    VegoBottomBarTabs.Favorites -> {
                        coreViewModel.favoritesClick()
                    }
                    VegoBottomBarTabs.Info -> {
                        coreViewModel.infoClick()
                    }
                    VegoBottomBarTabs.Cart -> {
                        coreViewModel.cartClick()
                    }
                }
            }
        }
    }
}