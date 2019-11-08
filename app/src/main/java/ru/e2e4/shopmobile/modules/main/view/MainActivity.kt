package ru.e2e4.shopmobile.modules.main.view

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.vision.barcode.Barcode
import kotlinx.android.synthetic.main.common_toolbar.vToolbar
import kotlinx.android.synthetic.main.main_activity.*
import ru.e2e4.barcode_reader.BarcodeReaderActivity
import ru.e2e4.shopmobile.R


class MainActivity : AppCompatActivity(R.layout.main_activity) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setSupportActionBar(vToolbar)

        vBottomNavigation.setOnNavigationItemReselectedListener {}
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.common_qcode, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.qcode -> {
                val launchIntent = BarcodeReaderActivity.getLaunchIntent(this, true, false)
                startActivityForResult(
                    launchIntent,
                    BARCODE_READER_ACTIVITY_REQUEST
                )
            }
        }
        return true
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode != Activity.RESULT_OK) {
            return
        }
        if (requestCode == BARCODE_READER_ACTIVITY_REQUEST && data != null) {
            val barcode =
                data.getParcelableExtra<Barcode>(BarcodeReaderActivity.KEY_CAPTURED_BARCODE)!!
            Toast.makeText(this, barcode.rawValue, Toast.LENGTH_SHORT)
                .show()
        }
    }

    companion object {
        const val BARCODE_READER_ACTIVITY_REQUEST = 1
    }
}
