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
import ru.e2e4.shopmobile.modules.search.data.SearchItem
import ru.e2e4.shopmobile.utils.hideKeyboard
import ru.e2e4.shopmobile.utils.showKeyboard
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
        showKeyboard(activity!!, vSearchEditText) // показываем клавиатуру
        vSearchEditText.afterTextChanged { presenter.inputtingText(it) }
        vSearchEditText.areConfirmButtonIsPressed { presenter.confirmInput(it) }
        vCleanTextButton.setOnClickListener { presenter.cleanInputSearch() }
        vSearchCleanHistoryText.setOnClickListener { presenter.cleanHistory() }
        presenter.loadSearchHistory()
        vToolbarSearch.setNavigationOnClickListener {
            hideKeyboard(activity!!, vSearchEditText)
            activity?.onBackPressed()
        }
    }

    override fun showSearchResult(list: List<SearchItem>) {
        adapterHistory.data = list
        adapterHistory.onClickListener = { presenter.selectSearchItem(it) }
    }

    override fun showSearchHistory(list: List<SearchItem>) {
        vMessageSearchText.visibility = View.INVISIBLE
        hideCategorySwitch()
        vSearchHistoryText.visibility = View.VISIBLE
        vSearchCleanHistoryText.visibility = View.VISIBLE
        adapterHistory.data = list
        adapterHistory.onClickListener = { presenter.selectHistoryItem(it) }
    }

    override fun showSearchMessage(message: String) {
        vSearchHistoryText.visibility = View.INVISIBLE
        vSearchCleanHistoryText.visibility = View.INVISIBLE
        vMessageSearchText.visibility = View.VISIBLE
        vMessageSearchText.text = message
    }

    override fun cleanInputSearch() {
        vSearchEditText.clean()
    }

    override fun setInputSearchText(text: String) {
        vSearchEditText.setText(text)
    }

    override fun hideCleanSearch() {
        vCleanTextButton.visibility = View.INVISIBLE
    }

    override fun showCleanSearch() {
        vCleanTextButton.visibility = View.VISIBLE
    }

    override fun showCategorySwitch() {
        vCategorySwitch.visibility = View.VISIBLE
    }

    override fun hideCategorySwitch() {
        vCategorySwitch.visibility = View.GONE
    }
}
