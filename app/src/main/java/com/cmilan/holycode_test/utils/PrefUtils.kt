package com.cmilan.holycode_test.utils

import android.content.SharedPreferences
import javax.inject.Inject

class PrefUtils
@Inject constructor(
    private val mPrefs: SharedPreferences
) {

    companion object {
        private const val ACC_TOK = "acc_tok"
        private const val BEARER = "Bearer "
        private const val REF_TOK = "ref_tok"
    }

    fun setAccessToken(accessToken: String?) = mPrefs.edit().putString(ACC_TOK, accessToken).apply()

    val accessToken get() = BEARER + mPrefs.getString(ACC_TOK, "")

    fun setRefreshToken(refresh: String?) = mPrefs.edit().putString(REF_TOK, refresh).apply()

    val refreshToken get() = mPrefs.getString(REF_TOK, "")

}