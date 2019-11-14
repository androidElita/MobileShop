package ru.e2e4.shopmobile.modules.search.view


import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.search_fragment.*
import kotlinx.android.synthetic.main.search_toolbar.*
import kotlinx.android.synthetic.main.search_toolbar.vToolbar
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

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (context.applicationContext as ComponentContract).getSearchComponent()
            .inject(this)
        presenter.attachView(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity).setSupportActionBar(vToolbar)
        vSearchHistoryRecyclerView.layoutManager = LinearLayoutManager(context)
        SoftKeyboardUtils.show(activity!!, vSearchEditText) // показываем клавиатуру
        vSearchEditText.afterTextChanged { presenter.inputtingText(it) }
        vSearchEditText.areConfirmButtonIsPressed { presenter.search(it) }
        vCleanTextButton.setOnClickListener { presenter.cleanInputSearch() }
        vSearchCleanHistoryText.setOnClickListener { presenter.cleanHistory() }
        presenter.loadSearchHistory()
//        vToolbar.setNavigationOnClickListener {
//           // SoftKeyboardUtils.hide(activity!!, vSearchEditText)
//            activity?.onBackPressed()
//        }
    }

    override fun showSearchHistory(list: List<SearchHistory>) {
        vSearchHistoryLayout.visibility = View.VISIBLE
        vSearchHistoryRecyclerView.adapter = SearchHistoryAdapter(list)
    }

    override fun showEmptyHistoryMessage() {
        vEmptyHistoryText.visibility = View.VISIBLE
    }

    override fun hideSearchHistory() {
        vSearchHistoryLayout.visibility = View.GONE
    }

    override fun hideEmptyHistoryMessage() {
        vEmptyHistoryText.visibility = View.GONE
    }

    override fun cleanInputSearch() {
        vSearchEditText.clean()
    }

    override fun hideCleanSearch() {
        vCleanTextButton.visibility = View.GONE
    }

    override fun showCleanSearch() {
        vCleanTextButton.visibility = View.VISIBLE
    }
}
