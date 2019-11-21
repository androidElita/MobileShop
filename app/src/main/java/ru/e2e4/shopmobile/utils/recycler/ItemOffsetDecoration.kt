package ru.e2e4.shopmobile.utils.recycler

import android.content.res.Resources
import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import ru.e2e4.shopmobile.R

class ItemOffsetDecoration(resources: Resources) : RecyclerView.ItemDecoration() {

    private val offset12 = resources.getDimension(R.dimen.value12).toInt()
    private val offset16 = resources.getDimension(R.dimen.value16).toInt()

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        with(outRect) {
            if (parent.getChildAdapterPosition(view) == 0) {
                top = offset16
            }

            val lastItemPosition = parent.adapter?.itemCount?.minus(1)
            bottom = if (parent.getChildAdapterPosition(view) == lastItemPosition) offset16
            else offset12

            right = offset16
            left = offset16
        }
    }
}