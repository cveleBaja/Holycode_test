package com.cmilan.holycode_test.ui.base

import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.cmilan.holycode_test.R
import com.cmilan.holycode_test.data.network.BaseHttpError
import com.cmilan.holycode_test.utils.ErrorHelper
import com.cmilan.holycode_test.utils.NetworkUtils
import com.google.android.material.snackbar.Snackbar

open class BaseActivity : AppCompatActivity() {

    private var mSnackBar: Snackbar? = null

    protected fun showCustomRedSnackBar(msg: String) {
        mSnackBar?.dismiss()

        val view: View = findViewById(R.id.main_container)
        mSnackBar = Snackbar.make(view, msg, Snackbar.LENGTH_LONG)
        mSnackBar?.view?.setBackgroundColor(
            ContextCompat.getColor(
                view.context,
                R.color.colorRed
            )
        )
        val tv =
            mSnackBar?.view?.findViewById<TextView>(com.google.android.material.R.id.snackbar_text)
        tv?.setTextColor(ContextCompat.getColor(view.context, R.color.white))

        mSnackBar!!.show()
    }

    protected fun showCustomGreenSnackBar(msg: String) {
        mSnackBar?.dismiss()

        val view: View = findViewById(R.id.main_container)
        mSnackBar = Snackbar.make(view, msg, Snackbar.LENGTH_LONG)
        mSnackBar?.view?.setBackgroundColor(
            ContextCompat.getColor(
                view.context,
                R.color.colorRed
            )
        )
        val tv =
            mSnackBar?.view?.findViewById<TextView>(com.google.android.material.R.id.snackbar_text)
        tv?.setTextColor(ContextCompat.getColor(view.context, R.color.white))

        mSnackBar!!.show()
    }

    fun showGeneralError(exception: Throwable) {
        if (!NetworkUtils.hasInternetAccess(this)) {
            showCustomRedSnackBar(getString(R.string.text_internet_off))
            return
        } else {
            if (ErrorHelper.showBackendError(this, exception)) {
                return
            }
            if (exception is BaseHttpError) {
                showCustomRedSnackBar(exception.errorMsg)
                return
            } else {
                showCustomRedSnackBar(getString(R.string.error_text_general))
            }
        }
    }
}