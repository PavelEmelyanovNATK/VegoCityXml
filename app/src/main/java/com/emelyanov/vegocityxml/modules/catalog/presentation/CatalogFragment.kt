package com.emelyanov.vegocityxml.modules.catalog.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.emelyanov.vegocityxml.R
import com.emelyanov.vegocityxml.databinding.FragmentCatalogBinding
import com.emelyanov.vegocityxml.modules.catalog.domain.CatalogViewModel
import com.emelyanov.vegocityxml.modules.catalog.domain.models.CatalogRecyclerViewAdapter
import com.emelyanov.vegocityxml.shared.domain.utils.AutofitGridLayoutManager
import com.emelyanov.vegocityxml.shared.domain.utils.RecyclerMarginDecoration
import com.emelyanov.vegocityxml.shared.domain.utils.dp
import dagger.hilt.android.AndroidEntryPoint

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [CatalogFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
@AndroidEntryPoint
class CatalogFragment : Fragment() {

    private lateinit var binding: FragmentCatalogBinding
    private val viewModel: CatalogViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentCatalogBinding.inflate(inflater, container, false)

        val manager = AutofitGridLayoutManager(requireContext(), 160.dp)
        val adapter = CatalogRecyclerViewAdapter()

        binding.catalogRecycler.layoutManager = manager
        binding.catalogRecycler.adapter = adapter

        return binding.root
    }
}