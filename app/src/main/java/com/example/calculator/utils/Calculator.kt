package com.example.calculator.utils

import com.example.calculator.`interface`.Calculator
import org.mariuszgromada.math.mxparser.Expression
import java.lang.Exception

class CalculatorImpl: Calculator {
    override fun calculate(expression: String): Double {
        var result: Double = Double.NaN
        try {
            result = Expression(expression).calculate()
        } catch (e: Exception) {
            println(e)
        }
        return result

    }
}