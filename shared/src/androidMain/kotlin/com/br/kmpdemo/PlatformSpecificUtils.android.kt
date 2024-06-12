package com.br.kmpdemo

import android.content.Context
import android.content.SharedPreferences
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import com.br.kmpdemo.utils.Constants.IS_METRIC
import com.br.kmpdemo.utils.Constants.MEASUREMENT_PREFS
import com.br.kmpdemo.utils.MeasurementType


actual object MeasurementPreference {
    private lateinit var preferences: SharedPreferences

    fun init(context: Context) {
        preferences = context.getSharedPreferences(MEASUREMENT_PREFS, Context.MODE_PRIVATE)
    }

    actual var preference: MeasurementType
        get() = if (preferences.getBoolean(IS_METRIC, false)) MeasurementType.IMPERIAL else MeasurementType.METRIC
        set(value) {
            preferences.edit().putBoolean(IS_METRIC, value == MeasurementType.METRIC).apply()
        }
}

fun MokoLocationProvider.bind(
    lifecycle: Lifecycle,
    context: Context,
    fragmentManager: FragmentManager
) {
    locationTracker.bind(lifecycle, context, fragmentManager)
}
