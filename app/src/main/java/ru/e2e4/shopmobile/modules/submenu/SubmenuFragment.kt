package ru.e2e4.shopmobile.modules.submenu


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.submenu_fragment.view.*
import ru.e2e4.shopmobile.R

class SubmenuFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.submenu_fragment, container, false)
        view.vFontButton.setOnClickListener {
            findNavController().navigate(R.id.action_navigation_submenu_to_fontFragment)
        }
        return view
    }
}
