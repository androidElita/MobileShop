package ru.e2e4.shopmobile.modules.catalog.view


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ru.e2e4.shopmobile.R

/**
 * A simple [Fragment] subclass.
 */
class GoodsListFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.catalog_fragment_goods_list, container, false)
    }


}
