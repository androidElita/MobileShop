package ru.e2e4.shopmobile.modules.catalog.view

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.catalog_fragment_categories.view.*
import ru.e2e4.shopmobile.R
import ru.e2e4.shopmobile.di.ComponentContract
import ru.e2e4.shopmobile.modules.catalog.adapters.CategoriesAdapter
import ru.e2e4.shopmobile.modules.catalog.contract.CategoriesContract.CategoryPresenter
import ru.e2e4.shopmobile.modules.catalog.contract.CategoriesContract.CategoryView
import ru.e2e4.shopmobile.modules.catalog.data.CategoriesNode
import ru.e2e4.shopmobile.utils.ItemOffsetDecoration
import javax.inject.Inject

class CategoriesFragment : Fragment(), CategoryView {

    @Inject
    lateinit var presenter: CategoryPresenter
    private lateinit var vCategories: RecyclerView

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (context.applicationContext as ComponentContract).getCategoriesComponent().inject(this)
        presenter.attachView(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.catalog_fragment_categories, container, false)
        presenter.loadCategoryTree()
        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        vCategories = view.vCategories
        vCategories.layoutManager = LinearLayoutManager(activity)
    }

    override fun showCategory(categories: CategoriesNode) {
        val adapter = CategoriesAdapter(categories.children).apply {
            onItemClickListener = { subcategory ->
                val action =
                    CategoriesFragmentDirections.actionCatalogFragmentToSubcategoriesFragment(
                        subcategory
                    )
                findNavController().navigate(action)
            }
        }
        vCategories.adapter = adapter
        vCategories.addItemDecoration(ItemOffsetDecoration(resources))
    }

    override fun showError() {
        Toast.makeText(activity, "Ошибка!", Toast.LENGTH_LONG).show()
    }

    override fun onDestroy() {
        presenter.detachView()
        super.onDestroy()
    }
}
