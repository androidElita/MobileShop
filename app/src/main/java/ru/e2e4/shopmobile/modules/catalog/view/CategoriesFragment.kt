package ru.e2e4.shopmobile.modules.catalog.view

import android.content.Context
import android.graphics.Rect
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.catalog_fragment_categories.*
import ru.e2e4.shopmobile.R
import ru.e2e4.shopmobile.di.ComponentContract
import ru.e2e4.shopmobile.modules.catalog.adapters.CategoriesAdapter
import ru.e2e4.shopmobile.modules.catalog.contract.CategoriesContract.CategoryPresenter
import ru.e2e4.shopmobile.modules.catalog.contract.CategoriesContract.CategoryView
import ru.e2e4.shopmobile.modules.catalog.data.CategoriesNode
import javax.inject.Inject

class CategoriesFragment : Fragment(), CategoryView {

    @Inject
    lateinit var presenter: CategoryPresenter

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (context.applicationContext as ComponentContract).getCatalogComponent().inject(this)
        presenter.attachView(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.catalog_fragment_categories, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        vCategories.layoutManager = LinearLayoutManager(activity)
        presenter.loadCategoriesTree()
    }

    override fun showCategories(categories: CategoriesNode) {
        val adapter = CategoriesAdapter(categories.children).apply {
            onItemClickListener = { subcategory ->
                val action =
                    CategoriesFragmentDirections.actionCategoriesFragmentToSubcategoriesFragment(
                        subcategory
                    )
                findNavController().navigate(action)
            }
        }
        vCategories.adapter = adapter
        vCategories.addItemDecoration(ItemDecoration())
    }

    override fun showError() {
        Toast.makeText(activity, "Ошибка!", Toast.LENGTH_LONG).show()
    }

    override fun onDestroy() {
        presenter.detachView()
        super.onDestroy()
    }

    inner class ItemDecoration : RecyclerView.ItemDecoration() {

        private val offset12 = resources.getDimension(R.dimen.value12).toInt()
        private val offset16 = resources.getDimension(R.dimen.value16).toInt()

        override fun getItemOffsets(
            outRect: Rect,
            view: View,
            parent: RecyclerView,
            state: RecyclerView.State
        ) {
            with(outRect) {
                if (parent.getChildAdapterPosition(view) == 0) top = offset16

                val lastItemPosition = parent.adapter?.itemCount?.minus(1)
                bottom = if (parent.getChildAdapterPosition(view) == lastItemPosition) offset16
                else offset12

                left = offset16
                right = offset16
            }
        }
    }
}