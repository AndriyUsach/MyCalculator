package com.example.calculator.utils

import com.example.calculator.`interface`.ExpressionBuilderInterface
import com.example.calculator.data.SymbolListData

class ExpressionBuilder : ExpressionBuilderInterface {

    private val expression = StringBuilder("0") // main expression for calculating

    // main function for adding characters to an expression
    override fun append(ch: Char) {
        when (ch) {
            '0' -> addZeroSymbol()
            '.' -> addDotSymbol()
            in SymbolListData.OPERATIONS.charList -> addOperationSymbol(ch)
            in SymbolListData.NUMBERS.charList -> addNumber(ch)
            else -> return
        }
    }

    override fun delete() {
        if (this.isEmpty()) return
        this.deleteSymbol()
    }

    private fun addZeroSymbol() {
        val lastNumber = getNumberList().last()

        if (lastNumber.isEmpty()) {
            this.addSymbol('0')
            return
        }
        if (lastNumber.contains('.')) {
            this.addSymbol('0')
            return
        }
        if (lastNumber.length == 1 && lastNumber.last() == '0') {
            return
        }
        this.addSymbol('0')
    }

    private fun addOperationSymbol(ch: Char) {
        val lastNumber = this.getNumberList().last()
        if (!this.isEmpty()) {
            if (this.isOperationSymbol(expression.lastIndex)) {
                this.deleteSymbol()
                this.addSymbol(ch)
                return
            }
        }

        if (lastNumber.isEmpty()) {
            this.addZeroSymbol()
            this.addSymbol(ch)
            return
        }

        if (lastNumber.last() == '.') {
            this.deleteSymbol()
            this.addSymbol(ch)
            return
        }

        this.addSymbol(ch)

    }

    private fun addNumber(ch: Char) {
        val lastNumber = this.getNumberList().last()
        if (lastNumber.isEmpty()) {
            this.addSymbol(ch)
            return
        }
        if (lastNumber.last() == '0' && lastNumber.length == 1) {
            deleteSymbol()
        }
        this.addSymbol(ch)
    }

    private fun addSymbol(ch: Char) {
        expression.append(ch)
    }

    private fun addDotSymbol() {
        val lastNumber = getNumberList().last()
        if (lastNumber.isEmpty()) {
            addZeroSymbol()
            this.addSymbol('.')
            return
        }
        if (lastNumber.contains('.')) {
            return
        }
        this.addSymbol('.')
    }

    private fun deleteSymbol(flag: Boolean = true) {
        expression.deleteCharAt(expression.lastIndex)
        if (this.isEmpty() && !flag) {
            expression.append('0')
        }
    }

    private fun isEmpty(): Boolean {
        return expression.isEmpty()
    }

    private fun isOperationSymbol(index: Int): Boolean {
        if (this.isEmpty()) return false
        return expression[index] in SymbolListData.OPERATIONS.charList
    }

    private fun getNumberList(): List<String> {
        return expression.toString().split('/', '*', '-', '+')
    }

    override fun clear() {
        expression.clear()
    }

    override fun getExpression(): String {
        return if (this.isEmpty()) "0" else expression.toString()
    }

    override fun setExpression(newExpression: String) {
        this.clear()
        expression.append(newExpression)
    }

    override fun isReadyForCalculation(): Boolean {
        if (this.isEmpty()) return true
        if (expression[expression.lastIndex] == '%') return true
        return !this.isOperationSymbol(expression.lastIndex)
    }
}