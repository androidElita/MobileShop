package ru.e2e4.shopmobile.modules.catalog.view

import android.graphics.Rect
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.catalog_fragment_subcategories.view.*
import ru.e2e4.shopmobile.R
import ru.e2e4.shopmobile.modules.catalog.adapters.CategoriesAdapter
import ru.e2e4.shopmobile.modules.catalog.contract.SubcategoriesContract.SubcategoriesView

class SubcategoriesFragment : Fragment(), SubcategoriesView {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.catalog_fragment_subcategories, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val category = SubcategoriesFragmentArgs.fromBundle(arguments!!).category
        view.vNameCategory.text = category.name

        view.vSubcategories.layoutManager =
            GridLayoutManager(activity, 3, GridLayoutManager.VERTICAL, false)

        val adapter = CategoriesAdapter(
            category.children,
            R.layout.catalog_recycler_subcategory_item
        )
        view.vSubcategories.layoutManager = GridLayoutManager(activity, 3)
        view.vSubcategories.addItemDecoration(GridItemDecoration())
        view.vSubcategories.adapter = adapter
    }

    inner class GridItemDecoration : RecyclerView.ItemDecoration() {

        private val offset14 = resources.getDimension(R.dimen.value14).toInt()
        private val offset16 = resources.getDimension(R.dimen.value16).toInt()
        private val offset24 = resources.getDimension(R.dimen.value24).toInt()

        override fun getItemOffsets(
            outRect: Rect,
            view: View,
            parent: RecyclerView,
            state: RecyclerView.State
        ) {

            val layoutParams = view.layoutParams as GridLayoutManager.LayoutParams

            layoutParams.spanIndex
        }
    }
}