package com.example.calculator.data

import com.example.calculator.R

enum class SymbolListData(val charList: List<Char>) {
    NUMBERS(listOf('1', '2', '3', '4', '5', '6', '7', '8', '9')),
    OPERATIONS(listOf('+', '-', '/', '*', '%'))
}

sealed class SymbolData {
    class Numbers : SymbolData() {
        companion object {
            val numbers = listOf('1', '2', '3', '4', '5', '6', '7', '8', '9')
        }
    }

    class Operations : SymbolData() {
        val operations = listOf('1', '2', '3', '4', '5', '6', '7', '8', '9')
    }

    class ButtonsId : SymbolData() {
        companion object {
            val numberButtonsId = listOf(R.string.zero, R.string.one, R.string.two, R.string.three, R.string.four,
                R.string.five, R.string.six, R.string.seven, R.string.eight, R.string.nine)
            val operationButtonsId = listOf(R.string.C, R.drawable.ic_twotone_arrow,
                R.drawable.percentage, R.drawable.divide, R.drawable.multiply, R.drawable.minus,
                R.drawable.plus,R.string.dot, R.drawable.equal)
        }

    }
}