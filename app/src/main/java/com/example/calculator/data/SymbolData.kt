package com.example.calculator.data

enum class SymbolListData(val charList: List<Char>) {
    NUMBERS(listOf('1', '2', '3', '4', '5', '6', '7', '8', '9')),
    OPERATIONS(listOf('+', '-', '/', '*', '%'))
}