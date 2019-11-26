package ru.e2e4.shopmobile.modules.catalog.view

import android.animation.ObjectAnimator
import android.graphics.Rect
import android.os.Bundle
import android.view.*
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.NestedScrollView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.catalog_fragment_subcategories.*
import kotlinx.android.synthetic.main.common_toolbar.*
import ru.e2e4.shopmobile.R
import ru.e2e4.shopmobile.modules.catalog.adapters.CategoriesAdapter

class SubcategoriesFragment : Fragment() {

    private var isTitleToolbarShown = false

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
        val category = SubcategoriesFragmentArgs.fromBundle(arguments!!).category

        (activity as AppCompatActivity).setSupportActionBar(vToolbar)
        vToolbar.setNavigationIcon(R.drawable.ic_arrow_back)
        vToolbar.setNavigationOnClickListener { onBackPressed() }
        if (isTitleToolbarShown) vToolbar.title = category.name

        vNameCategory.text = category.name

        val adapter = CategoriesAdapter(category.children)
        adapter.onItemClickListener = { subcategory ->
            val action =
                if (subcategory.children.isEmpty())
                    SubcategoriesFragmentDirections.actionSubcategoriesFragmentToGoodsListFragment()
                else
                    SubcategoriesFragmentDirections.actionSubcategoriesFragmentSelf(subcategory)
            findNavController().navigate(action)
        }

        vSubcategories.layoutManager = GridLayoutManager(activity, 3)
        vSubcategories.addItemDecoration(ItemDecoration())
        vSubcategories.adapter = adapter

        vNestedScroll.setOnScrollChangeListener(
            NestedScrollView.OnScrollChangeListener { _, _, scrollY, _, _ ->
                when {
                    scrollY > vNameCategory.height && !isTitleToolbarShown ->
                        showToolbarTitle(category.name)
                    scrollY < vNameCategory.height && isTitleToolbarShown ->
                        hideToolbarTitle()
                }
            }
        )
    }

    private fun showToolbarTitle(title: String) {
        isTitleToolbarShown = true
        val vToolbarTitle = getToolbarTitleView().apply { text = title }
        ObjectAnimator.ofFloat(vToolbarTitle, View.ALPHA, 0f, 1f)
            .setDuration(300)
            .start()
    }

    private fun hideToolbarTitle() {
        isTitleToolbarShown = false
        getToolbarTitleView().text = ""
    }

    private fun getToolbarTitleView(): TextView {
        val childCount = vToolbar.childCount
        repeat(childCount) {
            val child = vToolbar.getChildAt(it)
            if (child is TextView) return child
        }
        return TextView(activity)
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

    private fun onBackPressed() {
        (activity as AppCompatActivity).onBackPressed()
    }

    inner class ItemDecoration : RecyclerView.ItemDecoration() {

        private val offset2 = resources.getDimension(R.dimen.value2).toInt()
        private val offset9 = resources.getDimension(R.dimen.value9).toInt()
        private val offset16 = resources.getDimension(R.dimen.value16).toInt()
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