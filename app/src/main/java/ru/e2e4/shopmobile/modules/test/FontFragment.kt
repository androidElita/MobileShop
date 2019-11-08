package ru.e2e4.shopmobile.modules.test


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.test_fragment_font.view.*
import ru.e2e4.shopmobile.R

class FontFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.test_fragment_font, container, false)
        view.vId.text = arguments?.getString("id") ?: "Deeplink none"
        return view
    }
}
