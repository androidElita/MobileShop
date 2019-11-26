package ru.e2e4.shopmobile.modules.catalog.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.main_toolbar.*
import ru.e2e4.shopmobile.R

class GoodsListFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.catalog_fragment_goods_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        (activity as AppCompatActivity).setSupportActionBar(vToolbar)
        vToolbar.setNavigationIcon(R.drawable.ic_arrow_back)
        vToolbar.setNavigationOnClickListener { onBackPressed() }
    }

    private fun onBackPressed() {
        (activity as AppCompatActivity).onBackPressed()
    }
}