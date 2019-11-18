package ru.e2e4.shopmobile.modules.search.view


import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.search_fragment.*
import kotlinx.android.synthetic.main.search_toolbar.*
import kotlinx.android.synthetic.main.search_toolbar.vToolbarSearch

import ru.e2e4.shopmobile.R
import ru.e2e4.shopmobile.di.ComponentContract
import ru.e2e4.shopmobile.modules.search.adapter.SearchHistoryAdapter
import ru.e2e4.shopmobile.modules.search.contract.SearchPresenter
import ru.e2e4.shopmobile.modules.search.contract.SearchView
import ru.e2e4.shopmobile.room.search.SearchHistory
import ru.e2e4.shopmobile.utils.SoftKeyboardUtils
import javax.inject.Inject

class SearchFragment : Fragment(R.layout.search_fragment), SearchView {

    @Inject
    lateinit var presenter: SearchPresenter

    lateinit var adapterHistory: SearchHistoryAdapter

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (context.applicationContext as ComponentContract).getSearchComponent()
            .inject(this)
        presenter.attachView(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity).setSupportActionBar(vToolbarSearch)
        vSearchHistoryRecyclerView.layoutManager = LinearLayoutManager(context)
        adapterHistory = SearchHistoryAdapter()
        vSearchHistoryRecyclerView.adapter = adapterHistory
        SoftKeyboardUtils.show(activity!!, vSearchEditText) // показываем клавиатуру
        vSearchEditText.afterTextChanged { presenter.inputtingText(it) }
        vSearchEditText.areConfirmButtonIsPressed { presenter.search(it) }
        vCleanTextButton.setOnClickListener { presenter.cleanInputSearch() }
        vSearchCleanHistoryText.setOnClickListener { presenter.cleanHistory() }
        presenter.loadSearchHistory()
        vToolbarSearch.setNavigationOnClickListener {
            SoftKeyboardUtils.hide(activity!!, vSearchEditText)
            activity?.onBackPressed()
        }
    }

    override fun showSearchHistory(list: List<SearchHistory>) {
        vSearchHistoryText.visibility = View.VISIBLE
        vSearchCleanHistoryText.visibility = View.VISIBLE
        adapterHistory.data = list
    }

    override fun showEmptyHistoryMessage() {
        vEmptyHistoryText.visibility = View.VISIBLE
        adapterHistory.data = listOf()
    }

    override fun hideSearchHistory() {
        vSearchHistoryText.visibility = View.INVISIBLE
        vSearchCleanHistoryText.visibility = View.INVISIBLE
    }

    override fun hideEmptyHistoryMessage() {
        vEmptyHistoryText.visibility = View.INVISIBLE
    }

    override fun cleanInputSearch() {
        vSearchEditText.clean()
    }

    override fun hideCleanSearch() {
        vCleanTextButton.visibility = View.INVISIBLE
    }

    override fun showCleanSearch() {
        vCleanTextButton.visibility = View.VISIBLE
    }
}
