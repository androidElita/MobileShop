package ru.e2e4.shopmobile.modules.main.view

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.ui.setupActionBarWithNavController
import com.google.android.gms.vision.barcode.Barcode
import kotlinx.android.synthetic.main.common_toolbar.vToolbar
import kotlinx.android.synthetic.main.main_activity.*
import ru.e2e4.barcode_reader.BarcodeReaderActivity
import ru.e2e4.shopmobile.R
import ru.e2e4.shopmobile.utils.setupWithNavController


class MainActivity : AppCompatActivity(R.layout.main_activity) {

    private var currentNavController: LiveData<NavController>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setSupportActionBar(vToolbar)
        if (savedInstanceState == null) {
            setupBottomNavigationBar()
        } // Else, need to wait for onRestoreInstanceState
        /* TODO Прошлый вариант навигации по окнам
        val navController = Navigation.findNavController(this, R.id.nav_host_fragment)
        NavigationUI.setupWithNavController(vBottomNavigation, navController)
        vBottomNavigation.setOnNavigationItemReselectedListener {}*/
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        // Now that BottomNavigationBar has restored its instance state
        // and its selectedItemId, we can proceed with setting up the
        // BottomNavigationBar with Navigation
        setupBottomNavigationBar()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.common_qcode, menu)
        return true
    }

    /**
     * Called on first creation and when restoring state.
     */
    private fun setupBottomNavigationBar() {
        val navGraphIds = listOf(R.navigation.home, R.navigation.catalog, R.navigation.desires, R.navigation.cart, R.navigation.submenu)
        // Setup the bottom navigation view with a list of navigation graphs
        val controller = vBottomNavigation.setupWithNavController(
            navGraphIds = navGraphIds,
            fragmentManager = supportFragmentManager,
            containerId = R.id.nav_host_fragment,
            intent = intent
        )

        // Whenever the selected controller changes, setup the action bar.
        controller.observe(this, Observer { navController ->
            setupActionBarWithNavController(navController)
        })
        currentNavController = controller
    }

    override fun onSupportNavigateUp(): Boolean {
        return currentNavController?.value?.navigateUp() ?: false
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
