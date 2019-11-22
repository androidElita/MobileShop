package ru.e2e4.shopmobile.modules.catalog.view

import android.graphics.Rect
import android.os.Bundle
import android.view.*
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.catalog_fragment_subcategories.view.*
import kotlinx.android.synthetic.main.main_toolbar.*
import ru.e2e4.shopmobile.R
import ru.e2e4.shopmobile.modules.catalog.adapters.CategoriesAdapter
import ru.e2e4.shopmobile.modules.catalog.contract.SubcategoriesContract.SubcategoriesView

class SubcategoriesFragment : Fragment(), SubcategoriesView {

    private lateinit var rootView: View
    private var isHideTitleToolbar = true

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
        rootView = view
        val toolbar = view.vToolbar as Toolbar?
        (activity as AppCompatActivity).setSupportActionBar(toolbar)
        toolbar?.setNavigationIcon(R.drawable.ic_arrow_back)
        toolbar?.setNavigationOnClickListener { (activity as AppCompatActivity).onBackPressed() }

        val category = SubcategoriesFragmentArgs.fromBundle(arguments!!).category
        view.vNameCategory.text = category.name

        view.vSubcategories.layoutManager = GridLayoutManager(activity, 3)
        view.vSubcategories.addItemDecoration(GridItemDecoration())

        val adapter = CategoriesAdapter(category.children)
        adapter.onItemClickListener = { subcategory ->
            val action =
                SubcategoriesFragmentDirections.actionSubcategoriesFragmentSelf(subcategory)
            findNavController().navigate(action)
        }
        view.vSubcategories.adapter = adapter

        view.vScroll.viewTreeObserver.addOnScrollChangedListener {
            val scrollY = view.vScroll.scrollY
            if (scrollY > 100 && isHideTitleToolbar) {
                animateToolbarTitleHide(category.name, vToolbar)
            } else if (scrollY < 100 && !isHideTitleToolbar) animateToolbarTitleShow(" ", vToolbar)
        }
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

    private fun getToolbarTitleView(toolbar: Toolbar): TextView {
        val childCount = toolbar.childCount
        repeat(childCount) {
            val child = toolbar.getChildAt(it)
            if (child is TextView) return child
        }
        return TextView(activity)
    }

    private fun animateToolbarTitleHide(title: String, toolbar: Toolbar) {
        val vTitle = getToolbarTitleView(toolbar)
        vTitle.text = title
        val animationHide = AnimationUtils.loadAnimation(activity, R.anim.toolbar_title_hide)
        vTitle.startAnimation(animationHide)
        isHideTitleToolbar = false
    }

    private fun animateToolbarTitleShow(title: String, toolbar: Toolbar) {
        val vTitle = getToolbarTitleView(toolbar)
        val animationShow = AnimationUtils.loadAnimation(activity, R.anim.toolbar_title_show)
        vTitle.startAnimation(animationShow)
        animationShow.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationRepeat(animation: Animation?) {

            }

            override fun onAnimationEnd(animation: Animation?) {
                vTitle.text = " "
            }

            override fun onAnimationStart(animation: Animation?) {

            }
        })
        isHideTitleToolbar = true
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