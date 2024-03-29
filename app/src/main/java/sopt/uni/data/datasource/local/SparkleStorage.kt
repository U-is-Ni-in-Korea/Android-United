package sopt.uni.data.datasource.local

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import sopt.uni.BuildConfig

object SparkleStorage {
    private lateinit var pref: SharedPreferences
    private lateinit var prefTimer: SharedPreferences
    private lateinit var prefMemo: SharedPreferences

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

        prefTimer = context.getSharedPreferences(
            NAME,
            Context.MODE_PRIVATE,
        )

        prefMemo = context.getSharedPreferences(
            context.packageName,
            Context.MODE_PRIVATE,
        )
    }

    fun setUpdateAvailableBoolean(key: String, value: Boolean) {
        pref.edit { putBoolean(key, value) }
    }

    fun getUpdateAvailableBoolean(key: String): Boolean {
        return pref.getBoolean(key, false)
    }

    var accessToken: String?
        get() = pref.getString(ACCESS_TOKEN, null)
        set(value) = pref.edit { putString(ACCESS_TOKEN, value).apply() }

    // TODO refresh token 추가
    var refreshToken: String?
        get() = pref.getString(REFRESH_TOKEN, null)
        set(value) = pref.edit { putString(REFRESH_TOKEN, value).apply() }

    var userId: Int?
        get() = pref.getInt(USER_ID, -1)
        set(value) = pref.edit { putInt(USER_ID, value ?: -1).apply() }

    var partnerId: Int?
        get() = pref.getInt(PARTNER_ID, -1)
        set(value) = pref.edit { putInt(PARTNER_ID, value ?: -1).apply() }

    var coupleId: Int?
        get() = pref.getInt(COUPLE_ID, -1)
        set(value) = pref.edit { putInt(COUPLE_ID, value ?: -1).apply() }

    var inviteCode: String?
        get() = pref.getString(INVITE_CODE, EMPTY_TEXT)
        set(value) = pref.edit { putString(INVITE_CODE, value).apply() }

    var isActive: Boolean
        get() = prefTimer.getBoolean(ACTIVEKEY, false)
        set(value) = prefTimer.edit { putBoolean(ACTIVEKEY, value).apply() }

    var totalTime: Float
        get() = prefTimer.getFloat(TOTALTIMEKEY, 0F)
        set(value) = prefTimer.edit { putFloat(TOTALTIMEKEY, value).apply() }

    var isPause: Boolean
        get() = prefTimer.getBoolean(PAUSEKEY, false)
        set(value) = prefTimer.edit { putBoolean(PAUSEKEY, value).apply() }

    var remainTime: Long
        get() = prefTimer.getLong(REMAINTIMEKEY, totalTime.toLong())
        set(value) = prefTimer.edit { putLong(REMAINTIMEKEY, value).apply() }

    var memoText: String?
        get() = prefMemo.getString(MEMO_TEXT, EMPTY_TEXT)
        set(value) = prefMemo.edit { putString(MEMO_TEXT, value).apply() }

    fun clear() {
        pref.edit().clear().apply()
    }

    fun timerClear() {
        prefTimer.edit().clear().apply()
    }
}

const val ACCESS_TOKEN = "accessToken"
const val REFRESH_TOKEN = "refreshToken"
const val AUTH = "auth"
const val USER_ID = "userId"
const val PARTNER_ID = "partnerId"
const val COUPLE_ID = "coupleId"
const val INVITE_CODE = "inviteCode"
const val NAME = "timer_prefs"
const val ACTIVEKEY = "isTimerActive"
const val TOTALTIMEKEY = "totalTime"
const val PAUSEKEY = "isTimerPause"
const val REMAINTIMEKEY = "remainingSeconds"
const val MEMO_TEXT = "memoText"
const val EMPTY_TEXT = ""
