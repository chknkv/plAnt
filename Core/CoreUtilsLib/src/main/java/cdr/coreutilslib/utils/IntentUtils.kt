package cdr.coreutilslib.utils

import android.content.Intent
import android.os.Build
import java.io.Serializable

/**
 * Получение [Serializable] объекта из [Intent].
 * Данный метод используется для разных версий Android SDK.
 *
 * @author Alexandr Chekunkov
 */
@Suppress("DEPRECATION")
inline fun <reified T : Serializable> Intent.getSerializableObject(key: String): T? =
    if (Build.VERSION.SDK_INT > Build.VERSION_CODES.TIRAMISU) {
        this.getSerializableExtra(key, T::class.java)
    } else {
        this.getSerializableExtra(key) as? T
    }