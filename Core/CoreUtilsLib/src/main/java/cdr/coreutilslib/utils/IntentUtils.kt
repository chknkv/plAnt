package cdr.coreutilslib.utils

import android.content.Intent
import android.os.Build
import android.os.Parcelable

/**
 * Получение [Parcelable] объекта из [Intent].
 * Данный метод используется для разных версий Android SDK.
 *
 * @author Alexandr Chekunkov
 */
@Suppress("DEPRECATION")
inline fun <reified T : Parcelable> Intent.getSerializableObject(key: String): T? =
    if (Build.VERSION.SDK_INT > Build.VERSION_CODES.TIRAMISU) {
        this.getParcelableExtra(key, T::class.java)
    } else {
        this.getParcelableExtra(key) as? T
    }