package ru.e2e4.shopmobile.modules.search.view


import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.jakewharton.rxbinding3.widget.textChanges
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.search_fragment.*
import kotlinx.android.synthetic.main.search_toolbar.*
import kotlinx.android.synthetic.main.search_toolbar.vToolbarSearch

import ru.e2e4.shopmobile.R
import ru.e2e4.shopmobile.di.ComponentContract
import ru.e2e4.shopmobile.modules.search.adapter.SearchAdapter
import ru.e2e4.shopmobile.modules.search.contract.SearchPresenter
import ru.e2e4.shopmobile.modules.search.contract.SearchView
import ru.e2e4.shopmobile.modules.search.data.SearchItem
import ru.e2e4.shopmobile.modules.search.data.SearchType
import ru.e2e4.shopmobile.utils.hideKeyboard
import ru.e2e4.shopmobile.utils.showKeyboard
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class SearchFragment : Fragment(R.layout.search_fragment), SearchView {

    @Inject
    lateinit var presenter: SearchPresenter

    private lateinit var adapter: SearchAdapter

    private var disposablies = CompositeDisposable()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (context.applicationContext as ComponentContract).getSearchComponent()
            .inject(this)
        presenter.attachView(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity).setSupportActionBar(vToolbarSearch)
        adapter = SearchAdapter()
        vSearchHistoryRecyclerView.layoutManager = LinearLayoutManager(context)
        vSearchHistoryRecyclerView.adapter = adapter
        adapter.onClickListener = {
            when(it.type) {
                SearchType.HISTORY -> presenter.selectHistoryItem(it)
                SearchType.SEARCH_RESULT -> presenter.selectSearchItem(it)
            }
        }
        showKeyboard(activity!!, vSearchEditText) // показываем клавиатуру
        disposablies.add(
            vSearchEditText.textChanges()
                .debounce(INPUT_PROCESSING_INTERVAL, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    presenter.search(it.toString())
                }, {
                    // TODO
                })
        )
        vSearchEditText.afterTextChanged { presenter.inputtingText(it) }
        vSearchEditText.areConfirmButtonIsPressed { presenter.confirmInput(it) }
        vCleanTextButton.setOnClickListener { presenter.cleanInputSearch() }
        vSearchCleanHistoryText.setOnClickListener { presenter.cleanHistory() }
        vToolbarSearch.setNavigationOnClickListener {
            hideKeyboard(activity!!, vSearchEditText)
            activity?.onBackPressed()
        }
    }

    override fun onResume() {
        super.onResume()
        presenter.loadSearchHistory()
    }

    override fun showSearchResult(list: List<SearchItem>) {
       // if (vSearchEditText.text?.isEmpty() == true) {
      //      presenter.loadSearchHistory()
       // } else
        adapter.data = list
    }

    override fun showSearchHistory(list: List<SearchItem>) {
        vMessageSearchText.visibility = View.INVISIBLE
        hideCategorySwitch()
        vSearchHistoryText.visibility = View.VISIBLE
        vSearchCleanHistoryText.visibility = View.VISIBLE
        adapter.data = list
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

    override fun getInputSearchText(): String {
        return vSearchEditText.text.toString()
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

    override fun showLoadingAction() {
        vLoadingProgressBar.show()
    }

    override fun hideLoadingAction() {
        vLoadingProgressBar.hide()
    }

    override fun onDestroy() {
        presenter.detachView()
        disposablies.dispose()
        super.onDestroy()
    }

    companion object {
        const val INPUT_PROCESSING_INTERVAL = 200L
    }
}
