package com.example.navigation.extension

import android.net.Uri
import android.os.Bundle
import androidx.annotation.IdRes
import androidx.navigation.NavController
import androidx.navigation.NavDirections
import androidx.navigation.NavOptions
import timber.log.Timber

@SuppressWarnings("StringLiteralDuplication")
fun NavController.navigateSafe(
    direction: NavDirections,
    navOptions: NavOptions? = null,
) {
    return try {
        navigate(direction, navOptions)
    } catch (ex: IllegalArgumentException) {
        Timber.e("Error: ${ex.localizedMessage}")
    }
}

fun NavController.navigateSafe(
    @IdRes resId: Int,
    args: Bundle? = null,
    navOptions: NavOptions? = null,
) {
    return try {
        navigate(resId, args, navOptions)
    } catch (ex: IllegalArgumentException) {
        Timber.e("Error: ${ex.localizedMessage}")
    }
}

fun NavController.navigateSafe(
    deepLink: Uri
): Boolean {
    return try {
        navigate(deepLink)
        true
    } catch (ex: IllegalArgumentException) {
        Timber.e("Error: ${ex.localizedMessage}")
        false
    }
}
