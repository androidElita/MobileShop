package ru.e2e4.shopmobile.modules.catalog.view

import android.graphics.Rect
import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.catalog_fragment_subcategories.view.*
import ru.e2e4.shopmobile.R
import ru.e2e4.shopmobile.modules.catalog.adapters.CategoriesAdapter
import ru.e2e4.shopmobile.modules.catalog.contract.SubcategoriesContract.SubcategoriesView

class SubcategoriesFragment : Fragment(), SubcategoriesView {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.catalog_fragment_subcategories, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val toolbar = view.vToolbar as Toolbar?
        (activity as AppCompatActivity).setSupportActionBar(toolbar)
        toolbar?.setNavigationIcon(R.drawable.ic_arrow_back)
        toolbar?.setNavigationOnClickListener { (activity as AppCompatActivity).onBackPressed() }

        val category = SubcategoriesFragmentArgs.fromBundle(arguments!!).category
        view.vNameCategory.text = category.name

        view.vSubcategories.layoutManager =
            GridLayoutManager(activity, 3, GridLayoutManager.VERTICAL, false)

        val adapter = CategoriesAdapter(
            category.children,
            R.layout.catalog_recycler_subcategory_item
        )
        adapter.onItemClickListener = { subcategory ->
            val action =
                SubcategoriesFragmentDirections.actionSubcategoriesFragmentSelf(subcategory)
            findNavController().navigate(action)
        }
        view.vSubcategories.layoutManager = GridLayoutManager(activity, 3)
        view.vSubcategories.addItemDecoration(GridItemDecoration())
        view.vSubcategories.adapter = adapter
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.search, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.search -> {
                //TODO обработка нажания на меню поиска
            }
        }
        return true
    }

    inner class GridItemDecoration : RecyclerView.ItemDecoration() {

        private val offset12 = resources.getDimension(R.dimen.value12).toInt()
        private val offset16 = resources.getDimension(R.dimen.value16).toInt()
        private val offset3 = resources.getDimension(R.dimen.value3).toInt()
        private val offset2 = resources.getDimension(R.dimen.value2).toInt()
        private val offset9 = resources.getDimension(R.dimen.value9).toInt()
        private val offset24 = resources.getDimension(R.dimen.value24).toInt()

        override fun getItemOffsets(
            outRect: Rect,
            view: View,
            parent: RecyclerView,
            state: RecyclerView.State
        ) {
            val layoutParams = view.layoutParams as GridLayoutManager.LayoutParams
            with(outRect) {
                top = offset24
                when (layoutParams.spanIndex) {
                    0 -> {
                        left = offset16
                        right = offset2
                    }
                    1 -> {
                        left = offset9
                        right = offset9
                    }
                    2 -> {
                        left = offset2
                        right = offset16
                    }
                }
            }
        }
    }
}