package ru.e2e4.shopmobile.modules.category.view


import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import ru.e2e4.shopmobile.R
import ru.e2e4.shopmobile.di.ComponentContract
import ru.e2e4.shopmobile.modules.category.contract.CategoryPresenter
import ru.e2e4.shopmobile.modules.category.contract.CategoryView
import ru.e2e4.shopmobile.modules.category.data.CategoryNode
import javax.inject.Inject

class CategoryFragment : Fragment(), CategoryView {

    @Inject
    lateinit var presenter: CategoryPresenter

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (context.applicationContext as ComponentContract).getCategoryComponent()
            .inject(this)
        presenter.attachView(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.catalog_fragment, container, false)
    }

    override fun showCategory(categories: CategoryNode) {
        Toast.makeText(activity, categories.name, Toast.LENGTH_LONG).show()
    }

    override fun showError() {
        Toast.makeText(activity, "Ошибка!", Toast.LENGTH_LONG).show()
    }
}