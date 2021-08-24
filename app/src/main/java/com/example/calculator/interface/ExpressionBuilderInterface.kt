package com.example.calculator.`interface`

interface ExpressionBuilder {
    fun append(ch: Char)
    fun delete()
    fun clear()
    fun getExpression(): String
    fun setExpression(newExpression: String)
    fun isReadyForCalculation(): Boolean
}