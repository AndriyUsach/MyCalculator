package com.example.calculator.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.calculator.Screen.BasicCalculatorScreen
import com.example.calculator.Screen.ui.theme.CalculatorTheme

class BasicCalculatorFragment: Fragment() {

    private val viewModel: BasicCalculatorViewModel by viewModels { BasicCalculatorViewModelFactory() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                CalculatorTheme {
                    viewModel.expressionViewModel.observeAsState().value?.let { expression ->
                        BasicCalculatorScreen(
                            expression = expression,
                            result = viewModel.getResult(),
                            textCalculatorFieldSettings = viewModel.textCalculatorFieldSettings,
                            ButtonPressed = { ch -> viewModel.addChar(ch) },
                            deleteButtonPressed = { viewModel.deleteChar() },
                            clearButtonPressed = { viewModel.clearExpression() },
                            equalButtonPressed = { viewModel.equalExpression() }
                        )
                    }
                }
            }
        }
    }
}