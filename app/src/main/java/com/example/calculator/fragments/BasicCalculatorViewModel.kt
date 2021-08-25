package com.example.calculator.fragments

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.calculator.utils.CalculatorImpl
import com.example.calculator.utils.ExpressionBuilderImpl


class BasicCalculatorViewModel : ViewModel() {

    private val expressionBuilder = ExpressionBuilderImpl()
    private val _expressionViewModel = MutableLiveData("0")
    val expressionViewModel: LiveData<String>
        get() = _expressionViewModel

    private val calculator = CalculatorImpl()
    private var result: Double = 0.0
    private var equalButtonFlag = false

    var textCalculatorFieldSettings = TextCalculatorFieldSettings()
        private set


    fun addChar(ch: Char) {
        if (equalButtonFlag) {
            expressionBuilder.setExpression(result.toString())
            equalButtonFlag = false
        }

        textCalculatorFieldSettings.expressionSettings()
        expressionBuilder.append(ch)
        if (ch == '%') {
            _expressionViewModel.value =
                calculator.calculate(expressionBuilder.getExpression()).toString()
            expressionBuilder.setExpression(calculateExpression().toString())
            return
        }
        _expressionViewModel.value = expressionBuilder.getExpression()
    }

    fun deleteChar() {
        if (equalButtonFlag)
            return
        expressionBuilder.delete()
        _expressionViewModel.value = expressionBuilder.getExpression()
        textCalculatorFieldSettings.expressionSettings()

        if (_expressionViewModel.value == "0") {
            textCalculatorFieldSettings.defaultSettings()
        }
    }

    fun clearExpression() {
        expressionBuilder.clear()
        _expressionViewModel.value = expressionBuilder.getExpression()
        textCalculatorFieldSettings.defaultSettings()
    }

    private fun calculateExpression(): Double {
        if (expressionBuilder.isReadyForCalculation())
            result = calculator.calculate(_expressionViewModel.value!!)
        return result
    }

    fun getResult(): String {
        return "=${calculateExpression()}"
    }

    fun equalExpression() {
        expressionBuilder.append('1')
        _expressionViewModel.value = expressionBuilder.getExpression()
        expressionBuilder.delete()
        val value = expressionBuilder.getExpression()
        //expressionBuilder.setExpression(result.toString())
        _expressionViewModel.value = value
        textCalculatorFieldSettings.resultSettings()
        equalButtonFlag = true
    }

}


class BasicCalculatorViewModelFactory : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(BasicCalculatorViewModel::class.java)) {
            return BasicCalculatorViewModel() as T
        } else {
            throw IllegalArgumentException("Unknown view model class")
        }
    }

}