package me.himanshusoni.basicextensions

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager


fun Activity.hideKeyboard() {
    val f = this.currentFocus
    hideKeyboard(f)
}

fun Activity.hideKeyboard(f: View?) {
    val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    if (null != f && null != f.windowToken) {//&& EditText.class.isAssignableFrom(f.getClass())
        imm.hideSoftInputFromWindow(f.windowToken, 0)
    } else {
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN)
    }
}