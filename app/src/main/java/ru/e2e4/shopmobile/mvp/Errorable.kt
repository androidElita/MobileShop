package ru.e2e4.shopmobile.mvp

import androidx.fragment.app.Fragment

interface Errorable {

    fun showError(message: String? = null, description: String? = null, draw: String? = null) {
        if (this is Fragment) {
            // activity?.supportFragmentManager.
        }

    }

}