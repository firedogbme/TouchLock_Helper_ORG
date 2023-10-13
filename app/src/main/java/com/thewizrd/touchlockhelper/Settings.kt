import android.content.Context
import android.preference.PreferenceManager

object Settings {
    const val KEY_AUTOLAUNCH = "key_autolaunchtouchlock"

    fun isAutoLaunchTouchLockEnabled(context: Context): Boolean =
        context.getSharedPreferences(
            PreferenceManager.getDefaultSharedPreferencesName(context),
            Context.MODE_PRIVATE
        ).getBoolean(KEY_AUTOLAUNCH, false)

    fun setAutoLaunchTouchLockEnabled(context: Context, enabled: Boolean) {
        context.getSharedPreferences(
            PreferenceManager.getDefaultSharedPreferencesName(context),
            Context.MODE_PRIVATE
        ).edit()
            .putBoolean(KEY_AUTOLAUNCH, enabled)
            .apply()
    }
}
