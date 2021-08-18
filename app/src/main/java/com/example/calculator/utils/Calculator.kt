package com.example.calculator.utils

import com.example.calculator.`interface`.CalculatorInterface
import org.mariuszgromada.math.mxparser.Expression
import java.lang.Exception

class Calculator: CalculatorInterface {
    override fun calculate(expression: String): Double {
        var result: Double = Double.NaN
        try {
            result = Expression(expression).calculate()
        } catch (e: Exception) {
            println(e)
        } finally {
            return result
        }
    }
}