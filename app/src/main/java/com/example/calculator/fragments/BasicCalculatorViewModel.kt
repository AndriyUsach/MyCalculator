package com.example.calculator.fragments

import androidx.compose.ui.unit.sp
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.calculator.utils.CalculatorImpl
import com.example.calculator.utils.ExpressionBuilderImpl


class BasicCalculatorViewModel : ViewModel() {

    private val expressionBuilder = ExpressionBuilderImpl()
    private val _expressionViewModel = MutableLiveData<String>("0")
    val expressionViewModel: LiveData<String>
        get() = _expressionViewModel

    private val calculator = CalculatorImpl()
    private var result: Double = 0.0

    var textCalculatorFieldSettings = TextCalculatorFieldSettings()
        private set

    fun addChar(ch: Char) {
        expressionBuilder.append(ch)
        _expressionViewModel.value = expressionBuilder.getExpression()
        textCalculatorFieldSettings.expressionSettings()
    }

    fun deleteChar() {
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

    fun getResult(): String {
        result = calculator.calculate(_expressionViewModel.value!!)
        return "=$result"
    }

    fun equalExpression() {
        expressionBuilder.append('1')
        _expressionViewModel.value = expressionBuilder.getExpression()
        expressionBuilder.delete()
        val value = expressionBuilder.getExpression()
        expressionBuilder.setExpression(result.toString())
        _expressionViewModel.value = value
        textCalculatorFieldSettings.resultSettings()
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