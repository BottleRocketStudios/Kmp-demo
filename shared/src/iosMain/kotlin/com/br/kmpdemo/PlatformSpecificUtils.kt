package com.br.kmpdemo
import com.br.kmpdemo.utils.Constants.IS_METRIC
import com.br.kmpdemo.utils.MeasurementType
import platform.Foundation.NSUserDefaults


actual object MeasurementPreference {
    private val userDefaults: NSUserDefaults = NSUserDefaults.standardUserDefaults

    actual var preference: MeasurementType
        get() = if (userDefaults.boolForKey(IS_METRIC)) MeasurementType.METRIC else MeasurementType.IMPERIAL
        set(value) {
            userDefaults.setBool(value == MeasurementType.METRIC, IS_METRIC)
            userDefaults.synchronize()
        }
}

