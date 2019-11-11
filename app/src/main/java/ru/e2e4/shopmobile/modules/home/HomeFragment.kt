package ru.e2e4.shopmobile.modules.home


import android.app.Activity
import android.app.SearchManager
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.android.gms.vision.barcode.Barcode
import kotlinx.android.synthetic.main.home_fragment.view.*
import kotlinx.android.synthetic.main.main_toolbar.*
import ru.e2e4.barcode_reader.BarcodeReaderActivity
import ru.e2e4.shopmobile.R

class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.home_fragment, container, false)
        view.vText.text = arguments?.getString("id") ?: getString(R.string.home)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity).setSupportActionBar(vToolbar)
        vQrCodeButton.setOnClickListener {
            val launchIntent = BarcodeReaderActivity.getLaunchIntent(activity, true, false)
            startActivityForResult(launchIntent, BARCODE_READER_ACTIVITY_REQUEST)
        }
//        vSearchLayout.setOnClickListener {
//            findNavController().navigate(R.id.action_homeFragment_to_searchFragment)
//        }
        if (Intent.ACTION_SEARCH == (activity as AppCompatActivity).intent.action) {
            (activity as AppCompatActivity).intent.getStringExtra(SearchManager.QUERY)?.also { query ->
                Toast.makeText(activity, query, Toast.LENGTH_LONG)
                    .show()
            }
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && requestCode == BARCODE_READER_ACTIVITY_REQUEST) {
            data?.getParcelableExtra<Barcode>(BarcodeReaderActivity.KEY_CAPTURED_BARCODE)?.let {
                val address = Uri.parse(it.rawValue)
                val deeplink = Uri.parse(getString(R.string.deeplink))
                if (address.authority == deeplink.authority) {
                    findNavController().navigate(address)
                }
            }
        }
    }

    companion object {
        const val BARCODE_READER_ACTIVITY_REQUEST = 1
    }
}
