package ru.e2e4.shopmobile.modules.search.view


import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.Toast
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

class SearchFragment : Fragment(), SearchView {

    @Inject
    lateinit var presenter: SearchPresenter

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (context.applicationContext as ComponentContract).getSearchComponent()
            .inject(this)
        presenter.attachView(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.search_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity).setSupportActionBar(vToolbar)
        // показываем клавиатуру
        SoftKeyboardUtils.show(activity!!, vSearchEditText)
        vSearchEditText.addTextChangedListener(object : TextWatcher{
            override fun afterTextChanged(p0: Editable?) {
                vCleanTextButton.visibility = if (vSearchEditText.text.isNotEmpty())
                    View.VISIBLE else View.GONE
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }
        })
        vSearchEditText.setOnEditorActionListener { v, actionId, event ->
            if (areConfirmButtonIsPressed(actionId, event)) {
                val input = v.text
                    .toString()
                presenter.addSearchHistory(input)
                vSearchEditText.setText("")
                presenter.loadSearchHistory()
                return@setOnEditorActionListener true
            }
            false
        }
        vCleanTextButton.setOnClickListener {
            vSearchEditText.setText("")
        }
        vSearchCleanHistoryText.setOnClickListener {
            presenter.cleanHistory()
            presenter.loadSearchHistory()
            Toast.makeText(activity!!, getString(R.string.searchCleanComplete), Toast.LENGTH_SHORT)
                .show()
        }
        vToolbar.setNavigationOnClickListener {
           // SoftKeyboardUtils.hide(activity!!, vSearchEditText)
            activity?.onBackPressed()
        }
        vSearchHistoryRecyclerView.layoutManager = LinearLayoutManager(context)
        presenter.loadSearchHistory()
    }

    private fun areConfirmButtonIsPressed(actionId: Int, event: KeyEvent?): Boolean {
        return (actionId == EditorInfo.IME_ACTION_SEARCH
                || actionId == EditorInfo.IME_ACTION_DONE
                || event == null
                || event.keyCode == KeyEvent.KEYCODE_ENTER && event.action == KeyEvent.ACTION_DOWN)
    }


    override fun showSearchHistory(list: List<SearchHistory>) {
        vSearchHistoryLayout.visibility = View.VISIBLE
        vEmptyHistoryText.visibility = View.GONE
        vSearchHistoryRecyclerView.adapter = SearchHistoryAdapter(list)
    }

    override fun showEmptyHistoryMessage() {
        vSearchHistoryLayout.visibility = View.GONE
        vEmptyHistoryText.visibility = View.VISIBLE
    }
}
