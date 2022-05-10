package com.emelyanov.vegocityxml.modules.catalog.domain.models

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.emelyanov.vegocityxml.databinding.FragmentCatalogBinding
import com.emelyanov.vegocityxml.databinding.ProductCardBinding

class CatalogRecyclerViewAdapter : RecyclerView.Adapter<CatalogItemViewHolder>() {

    var products: List<Int> = (1..43).toList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatalogItemViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ProductCardBinding.inflate(inflater, parent, false)
        return CatalogItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CatalogItemViewHolder, position: Int) {

    }

    override fun getItemCount(): Int = products.size
}