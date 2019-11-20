package ru.e2e4.shopmobile.modules.catalog.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.catalog_fragment_subcategories.view.*
import ru.e2e4.shopmobile.R
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
        view.vNameCategory.text =
            SubcategoriesFragmentArgs.fromBundle(arguments!!).subcategories.name
    }
}