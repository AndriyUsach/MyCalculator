package com.example.calculator.presenter

import android.graphics.Color
import com.example.calculator.`interface`.*
import com.example.calculator.data.SymbolListData
import com.example.calculator.utils.Calculator
import com.example.calculator.utils.ExpressionBuilder

class CalculatorPresenter(private val textFragment: TextResultInterface) : PresenterInterface,
    ButtonClickerInterface{

    private val calculator: CalculatorInterface = Calculator()
    private val expressionBuilder: ExpressionBuilderInterface = ExpressionBuilder()
    private var expressionResult: String = ""
    private var equalFlag: Boolean = false

    private fun takeInputExpression(expression: String, calculateFlag: Boolean) {
        if (expression.isEmpty()) {
            clearView()
            return
        }

        if (calculateFlag) {
            val result = calculator.calculate(expression)
            expressionResult = result.toString()
            showResult(result.toString())
            if (expression[expression.length - 1] == '%') {
                this.expressionBuilder.setExpression(result.toString())
                showExpression(result.toString())
                return
            }
        }

        showExpression(expression)
    }

    private fun showResult(result: String) {
        textFragment.apply {
            changeTextResultView(result)
            changeTextSizeResultView(30f)
            changeColorTextResultView(Color.GRAY)
        }
    }

    private fun showExpression(expression: String) {
        textFragment.apply {
            changeTextExpressionView(expression)
            changeTextSizeExpressionView(40f)
            changeColorTexExpressionView(Color.BLACK)
            changeColorTextResultView(Color.GRAY)
            changeTextSizeResultView(30f)
        }
    }

    private fun clearView() {
        textFragment.apply {
            changeTextExpressionView("")
            changeTextResultView("")
            changeTextSizeExpressionView(0f)
            changeTextSizeResultView(0f)
        }
    }

    override fun addChar(ch: Char) {
        var flag = false
        if (equalFlag) {
            flag = this.afterEqualAction(ch)
        }
        if (flag) {
            expressionBuilder.clear()
        }
        this.expressionBuilder.append(ch)
        takeInputExpression(expressionBuilder.getExpression(), expressionBuilder.isReadyForCalculation())
    }

    override fun removeChar() {
        this.expressionBuilder.delete()
        takeInputExpression(expressionBuilder.getExpression(), expressionBuilder.isReadyForCalculation())
    }

    override fun removerAll() {
        this.expressionBuilder.clear()
        takeInputExpression(expressionBuilder.getExpression(), expressionBuilder.isReadyForCalculation())
    }

    override fun equal() {
        equalFlag = true
        // change text size
        textFragment.changeTextSizeResultView(40f)
        textFragment.changeTextSizeExpressionView(30f)

        // change text color
        textFragment.changeColorTextResultView(Color.BLACK)
        textFragment.changeColorTexExpressionView(Color.GRAY)

        // change current expression
        expressionBuilder.setExpression(expressionResult)
    }

    private fun afterEqualAction(ch: Char): Boolean {
        val flagOne = ch in SymbolListData.NUMBERS.charList
        val flagTwo = expressionResult == expressionBuilder.getExpression()
        return flagOne && flagTwo
    }
}