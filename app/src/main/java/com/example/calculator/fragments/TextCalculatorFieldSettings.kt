package com.example.calculator.fragments

import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp

class TextCalculatorFieldSettings(
    private val primaryFontSize: TextUnit = 40.sp,
    private val secondaryFontSize: TextUnit = 20.sp
) {

    var expressionFontSize: TextUnit = primaryFontSize
    var resultFontSize: TextUnit = 0.sp

    fun resultSettings() {
        expressionFontSize = secondaryFontSize
        resultFontSize = primaryFontSize
    }
    fun defaultSettings() {
        expressionFontSize = primaryFontSize
        resultFontSize = 0.sp
    }

    fun expressionSettings() {
        expressionFontSize = primaryFontSize
        resultFontSize = secondaryFontSize
    }
}