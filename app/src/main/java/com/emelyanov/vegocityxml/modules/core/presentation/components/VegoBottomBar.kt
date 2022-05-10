package com.emelyanov.vegocityxml.modules.core.presentation.components

import android.animation.AnimatorSet
import android.animation.ValueAnimator
import android.content.Context
import android.os.Parcel
import android.os.Parcelable
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.content.res.AppCompatResources.getDrawable
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat.getColor
import androidx.core.view.marginBottom
import androidx.core.view.updateLayoutParams
import androidx.navigation.NavController
import com.emelyanov.vegocityxml.R
import com.emelyanov.vegocityxml.databinding.VegoBottomBarBinding
import com.emelyanov.vegocityxml.shared.domain.utils.dp
import kotlin.math.roundToInt

enum class VegoBottomBarTabs{
    Catalog,
    Favorites,
    Info,
    Cart
}

private const val TRANSITION_DURATION = 200L
private const val DEFAULT_BOTTOM_MARGIN = 15

class VegoBottomBar
constructor(
    context: Context,
    attrSet: AttributeSet?,
    style: Int
) : ConstraintLayout(context, attrSet, style) {
    constructor(context: Context) : this(context, null, 0)
    constructor(context: Context, attrSet: AttributeSet) : this(context, attrSet, 0)

    private val binding: VegoBottomBarBinding

    private val whiteColor = getColor(context, R.color.white)
    private val saladColor = getColor(context, R.color.vego_salad)
    private val blackColor = getColor(context, R.color.black)

    private fun getAssociatedIcon(tab: VegoBottomBarTabs)
            = when(tab) {
        VegoBottomBarTabs.Catalog -> binding.catalogIcon
        VegoBottomBarTabs.Favorites -> binding.favoritesIcon
        VegoBottomBarTabs.Info -> binding.infoIcon
        VegoBottomBarTabs.Cart -> binding.cartIcon
    }

    private fun getAssociatedCaption(tab: VegoBottomBarTabs)
            = when(tab) {
        VegoBottomBarTabs.Catalog -> binding.catalogCaption
        VegoBottomBarTabs.Favorites -> binding.favoritesCaption
        VegoBottomBarTabs.Info -> binding.infoCaption
        VegoBottomBarTabs.Cart -> binding.cartCaption
    }

    var selectedTab: VegoBottomBarTabs = VegoBottomBarTabs.Catalog
        private set(value) {
            if(value == field) return

            val currentIcon = getAssociatedIcon(field)
            val currentCaption = getAssociatedCaption(field)

            val targetIcon = getAssociatedIcon(value)
            val targetCaption = getAssociatedCaption(value)

            val whiteToBlack = ValueAnimator.ofArgb(blackColor, whiteColor).apply {
                duration = TRANSITION_DURATION
                addUpdateListener {
                    targetIcon.setColorFilter(it.animatedValue as Int)
                    targetCaption.setTextColor(it.animatedValue as Int)
                }
            }

            val blackToWhite = ValueAnimator.ofArgb(whiteColor, blackColor).apply {
                duration = TRANSITION_DURATION
                addUpdateListener {
                    currentIcon.setColorFilter(it.animatedValue as Int)
                    currentCaption.setTextColor(it.animatedValue as Int)
                }
            }

            val params = binding.tabSelector.layoutParams as LayoutParams

            val offsetAnimation = ValueAnimator.ofFloat(
                params.horizontalBias,
                when(value) {
                    VegoBottomBarTabs.Catalog -> 0f
                    VegoBottomBarTabs.Favorites -> 0.33333f
                    VegoBottomBarTabs.Info -> 0.66666f
                    VegoBottomBarTabs.Cart -> 1f
                }
            ).apply {
                duration = 200
                addUpdateListener {
                    binding.tabSelector.updateLayoutParams<LayoutParams> {
                        horizontalBias = it.animatedValue as Float
                    }
                }
            }

            field = value

            AnimatorSet().apply {
                playTogether(offsetAnimation, whiteToBlack, blackToWhite)
                start()
            }
        }

    var isVisible: Boolean = true
        private set(value) {
            if(value == field) return

            val hiddenOffset = -(binding.root.height + DEFAULT_BOTTOM_MARGIN * 2).toFloat().dp
            val defaultOffset = DEFAULT_BOTTOM_MARGIN.toFloat().dp

            val anim = ValueAnimator.ofFloat(
                if(value) hiddenOffset else defaultOffset,
                if(field) hiddenOffset else defaultOffset
            ).apply {
                duration = 300

                addUpdateListener {
                    binding.root.updateLayoutParams<LayoutParams> {
                        bottomMargin = (it.animatedValue as Float).roundToInt()
                    }
                }
            }

            field = value

            anim.start()
        }

    init {
        with(LayoutInflater.from(context)) {
            inflate(R.layout.vego_bottom_bar, this@VegoBottomBar, true)
        }

        binding = VegoBottomBarBinding.bind(this)

        background = getDrawable(context, R.drawable.rounded_corner_shape_max)

        elevation = 2f.dp
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()

        if(isVisible)
            updateLayoutParams<LayoutParams> {
                marginStart = 15.dp
                marginEnd = 15.dp
                bottomMargin = DEFAULT_BOTTOM_MARGIN.dp
            }
        else
            updateLayoutParams<LayoutParams> {
                marginStart = 15.dp
                marginEnd = 15.dp
                bottomMargin = -(binding.root.height + DEFAULT_BOTTOM_MARGIN * 2).dp
            }
        Log.d("State", "Attached, BottomMargin: $marginBottom")
    }

    fun setOnClickListener(onClick: (VegoBottomBarTabs) -> Unit) {
        binding.catalogHitbox.setOnClickListener {
            onClick(VegoBottomBarTabs.Catalog)
        }
        binding.favoritesHitbox.setOnClickListener {
            onClick(VegoBottomBarTabs.Favorites)
        }
        binding.infoHitbox.setOnClickListener {
            onClick(VegoBottomBarTabs.Info)
        }
        binding.cartHitbox.setOnClickListener {
            onClick(VegoBottomBarTabs.Cart)
        }
    }

    fun setupWithNavController(navController: NavController) {
        navController.addOnDestinationChangedListener { _, dest, _ ->
            when(dest.id) {
                R.id.catalogFragment -> {
                    selectedTab = VegoBottomBarTabs.Catalog
                    isVisible = true
                }
                R.id.favoritesFragment -> {
                    selectedTab = VegoBottomBarTabs.Favorites
                    isVisible = true
                }
                R.id.infoFragment -> {
                    selectedTab = VegoBottomBarTabs.Info
                    isVisible = true
                }
                R.id.cartFragment -> {
                    selectedTab = VegoBottomBarTabs.Cart
                    isVisible = true
                }
                R.id.productDetails -> {
                    isVisible = false
                }
            }
        }
    }

    override fun onSaveInstanceState(): Parcelable? {
        val superState =  super.onSaveInstanceState()!!
        val savedState = VegoBottomBarSavedState(superState)
        savedState.isVisible = isVisible
        Log.d("SavingState", "Saved ${savedState.isVisible}")
        return savedState
    }

    override fun onRestoreInstanceState(state: Parcelable?) {
        val savedState = state as VegoBottomBarSavedState
        super.onRestoreInstanceState(state)
        isVisible = savedState.isVisible
        Log.d("RestoringState", "Restored $isVisible")
    }

    class VegoBottomBarSavedState : BaseSavedState {
        var isVisible = true

        constructor(superState: Parcelable) : super(superState)
        constructor(parcel: Parcel) : super(parcel) {
            isVisible = try {
                parcel.readString().toBoolean()
            } catch(ex: Exception) {
                true
            }
        }

        override fun writeToParcel(out: Parcel, flags: Int) {
            super.writeToParcel(out, flags)
            out.writeString(isVisible.toString())
        }

        companion object {
            @JvmField
            val CREATOR: Parcelable.Creator<VegoBottomBarSavedState> = object : Parcelable.Creator<VegoBottomBarSavedState> {
                override fun createFromParcel(source: Parcel): VegoBottomBarSavedState {
                    return VegoBottomBarSavedState(source)
                }
                override fun newArray(size: Int): Array<VegoBottomBarSavedState?> {
                    return Array(size) { null }
                }
            }
        }
    }
}
