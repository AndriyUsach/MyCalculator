package com.example.calculator.data

import com.example.calculator.R

sealed class SymbolData {
    class SymbolCharData : SymbolData() {
        companion object {
            val numberSymbolList = listOf(
                '7', '8', '9', '4', '5', '6', '1', '2', '3', '0', '.'
            )
            val operatorSymbolList = listOf(
                '%', '/', '*', '-', '+', '='
            )
            val numbersList = listOf(
                '1', '2', '3', '4', '5', '6', '7', '8', '9'
            )
        }
    }

    class SymbolDrawableIdData: SymbolData() {
        companion object {
            val operatorPainterResourceIdData = listOf(
                R.drawable.percentage, R.drawable.divide, R.drawable.multiply, R.drawable.minus,
                R.drawable.plus, R.drawable.equal
            )
            val numberPainterResourceIdData = listOf(
                R.drawable.seven, R.drawable.eight, R.drawable.nine,
                R.drawable.four, R.drawable.five, R.drawable.six,
                R.drawable.one, R.drawable.two, R.drawable.three,
                R.drawable.zero, R.drawable.dot
            )
        }
    }
}
