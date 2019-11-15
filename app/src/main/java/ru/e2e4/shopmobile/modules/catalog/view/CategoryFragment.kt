package ru.e2e4.shopmobile.modules.catalog.view

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.catalog_fragment.view.*
import ru.e2e4.shopmobile.R
import ru.e2e4.shopmobile.di.ComponentContract
import ru.e2e4.shopmobile.modules.catalog.adapters.CategoryAdapter
import ru.e2e4.shopmobile.modules.catalog.contract.CategoryPresenter
import ru.e2e4.shopmobile.modules.catalog.contract.CategoryView
import ru.e2e4.shopmobile.modules.catalog.data.CategoryNode
import ru.e2e4.shopmobile.utils.ItemOffsetDecoration
import javax.inject.Inject

class CategoryFragment : Fragment(), CategoryView {

    @Inject
    lateinit var presenter: CategoryPresenter
    private lateinit var vCategories: RecyclerView

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (context.applicationContext as ComponentContract).getCategoryComponent().inject(this)
        presenter.attachView(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.catalog_fragment, container, false)
        presenter.loadCategoryTree()
        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        vCategories = view.vCategories
        vCategories.layoutManager = LinearLayoutManager(activity)
    }

    override fun showCategory(categories: CategoryNode) {
        vCategories.adapter = CategoryAdapter(categories.children)
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
