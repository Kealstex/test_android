@file:JvmName("Converter")
package com.example.test

import androidx.databinding.InverseMethod

object Converter {

    @JvmStatic
    fun StringToInt(value: String): Int {
        return value.toInt()
    }

    @InverseMethod("StringToInt")
    @JvmStatic
    fun IntToString(value: Int): String {
        return value.toString()
    }

    @JvmStatic
    fun StringToFloat(newValue: String): Float {
        return newValue.toFloat()
    }
    @InverseMethod("StringToFloat")
    @JvmStatic
    fun FloatToString(oldValue: Float): String {
        return oldValue.toString()
    }
}