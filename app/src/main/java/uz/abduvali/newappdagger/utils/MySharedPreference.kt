package uz.abduvali.newappdagger.utils

import android.content.Context
import android.text.TextUtils
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MySharedPreference @Inject constructor(context: Context) {
    private val appSharedPrefs = context.getSharedPreferences("have", Context.MODE_PRIVATE)

    fun setPreferences(key: String?, value: String?) {
        val prefsEditor = appSharedPrefs?.edit()
        prefsEditor?.putString(key, value)
        prefsEditor?.apply()
    }

    fun getPreferences(key: String?): String? {
        val json = appSharedPrefs?.getString(key, "")
        return if (TextUtils.isEmpty(json)) {
            null
        } else json
    }
}