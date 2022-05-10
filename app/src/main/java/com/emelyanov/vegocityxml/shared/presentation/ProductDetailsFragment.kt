package com.emelyanov.vegocityxml.shared.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.emelyanov.vegocityxml.R
import com.emelyanov.vegocityxml.databinding.FragmentProductDetailsBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class ProductDetailsFragment : Fragment() {
    private lateinit var binging: FragmentProductDetailsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binging = FragmentProductDetailsBinding.inflate(inflater, container, false)

        return binging.root
    }
}