package ru.e2e4.shopmobile.modules.category.view


import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.main_toolbar.*
import ru.e2e4.shopmobile.R
import ru.e2e4.shopmobile.di.ComponentContract
import ru.e2e4.shopmobile.modules.category.contract.CategoryPresenter
import ru.e2e4.shopmobile.modules.category.contract.CategoryView
import ru.e2e4.shopmobile.modules.category.data.CategoryNode
import javax.inject.Inject

class CategoryFragment : Fragment(R.layout.catalog_fragment), CategoryView {

    @Inject
    lateinit var presenter: CategoryPresenter

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (context.applicationContext as ComponentContract).getCategoryComponent()
            .inject(this)
        presenter.attachView(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        vSearchLayout.setOnClickListener {
            findNavController().navigate(R.id.searchActivity2)
        }
    }

    override fun showCategory(categories: CategoryNode) {
        Toast.makeText(activity, categories.name, Toast.LENGTH_LONG).show()
    }

    override fun showError() {
        Toast.makeText(activity, "Ошибка!", Toast.LENGTH_LONG).show()
    }
}
