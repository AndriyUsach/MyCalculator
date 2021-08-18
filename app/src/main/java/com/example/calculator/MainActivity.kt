package com.example.calculator

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import com.example.calculator.`interface`.ButtonClickerInterface
import com.example.calculator.`interface`.PresenterInterface
import com.example.calculator.fragments.ButtonCalculatorFragment
import com.example.calculator.fragments.TextResultFragment
import com.example.calculator.presenter.CalculatorPresenter

class MainActivity : AppCompatActivity(){

    private lateinit var presenterCalculator: PresenterInterface
    private lateinit var textResultFragment: TextResultFragment
    private lateinit var buttonCalculatorFragment: ButtonCalculatorFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        textResultFragment = TextResultFragment()
        presenterCalculator = CalculatorPresenter(textResultFragment)
        buttonCalculatorFragment = ButtonCalculatorFragment(
            presenterCalculator as ButtonClickerInterface)

        setContentView(R.layout.activity_main)
        supportFragmentManager.commit {
            setReorderingAllowed(true)
            add(R.id.button_calculator_fragment, buttonCalculatorFragment)
            add(R.id.expression_fragment, textResultFragment)
        }
    }
}