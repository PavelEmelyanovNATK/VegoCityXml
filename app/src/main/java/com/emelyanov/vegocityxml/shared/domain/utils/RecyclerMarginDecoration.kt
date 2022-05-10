package com.emelyanov.vegocityxml.shared.domain.utils

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class RecyclerMarginDecoration(
    private val top: Int,
    private val between: Int,
    private val bottom: Int
) : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        with(outRect) {
            if (parent.getChildAdapterPosition(view) == 0) {
                top = this@RecyclerMarginDecoration.top
            }
            left =  between
            right = between

            bottom = if(parent.getChildAdapterPosition(view) == parent.childCount - 1)
                this@RecyclerMarginDecoration.bottom else between
        }
    }
}
