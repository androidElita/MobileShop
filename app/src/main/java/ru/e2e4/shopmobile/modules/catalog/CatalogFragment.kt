package ru.e2e4.shopmobile.modules.catalog


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import ru.e2e4.shopmobile.R

/**
 * A simple [Fragment] subclass.
 */
class CatalogFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.catalog_fragment, container, false)
    }


}
