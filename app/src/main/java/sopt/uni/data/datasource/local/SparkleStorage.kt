package sopt.uni.data.datasource.local

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import sopt.uni.BuildConfig

object SparkleStorage {
    private lateinit var pref: SharedPreferences

    fun init(context: Context) {
        val masterKeyAlias = MasterKey.Builder(context, MasterKey.DEFAULT_MASTER_KEY_ALIAS)
            .setKeyScheme(MasterKey.KeyScheme.AES256_GCM).build()

        pref = if (BuildConfig.DEBUG) {
            context.getSharedPreferences(
                AUTH,
                Context.MODE_PRIVATE,
            )
        } else {
            EncryptedSharedPreferences.create(
                context,
                context.packageName,
                masterKeyAlias,
                EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
                EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM,
            )
        }
    }

    var accessToken: String?
        get() = pref.getString(ACCESS_TOKEN, null)
        set(value) = pref.edit { putString(ACCESS_TOKEN, value).apply() }

    // TODO refresh token 추가
    var refreshToken: String?
        get() = pref.getString(REFRESH_TOKEN, null)
        set(value) = pref.edit { putString(REFRESH_TOKEN, value).apply() }

    fun clear() {
        pref.edit().clear().apply()
    }
}

const val ACCESS_TOKEN = "accessToken"
const val REFRESH_TOKEN = "refreshToken"
const val AUTH = "auth"
