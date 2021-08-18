package com.example.calculator.fragments

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import androidx.fragment.app.Fragment
import com.example.calculator.R
import com.example.calculator.`interface`.*


class ButtonCalculatorFragment(private val presenter: ButtonClickerInterface)
    : Fragment(R.layout.fragment_button_calculator) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // number buttons
        val zeroBtn = requireView().findViewById<Button>(R.id.zero_btn)
        val oneBtn = requireView().findViewById<Button>(R.id.one_btn)
        val twoBtn = requireView().findViewById<Button>(R.id.two_btn)
        val threeBtn = requireView().findViewById<Button>(R.id.three_btn)
        val fourBtn = requireView().findViewById<Button>(R.id.four_btn)
        val fiveBtn = requireView().findViewById<Button>(R.id.five_btn)
        val sixBtn = requireView().findViewById<Button>(R.id.six_btn)
        val sevenBtn = requireView().findViewById<Button>(R.id.seven_btn)
        val eightBtn = requireView().findViewById<Button>(R.id.eight_btn)
        val nineBtn = requireView().findViewById<Button>(R.id.nine_btn)

        // arithmetic buttons
        val plusBtn = requireView().findViewById<ImageButton>(R.id.plus_btn)
        val minusBtn = requireView().findViewById<ImageButton>(R.id.minus_btn)
        val divisionBtn = requireView().findViewById<Button>(R.id.division_btn)
        val multiplyBtn = requireView().findViewById<ImageButton>(R.id.multiply_btn)
        val percentBtn = requireView().findViewById<Button>(R.id.percent_btn)
        val dotBtn = requireView().findViewById<Button>(R.id.dot_btn)

        // service buttons
        val calculateBtn = requireView().findViewById<ImageButton>(R.id.calculate_btn)
        val backspaceBtn = requireView().findViewById<ImageButton>(R.id.backspace_btn)
        val clearBtn = requireView().findViewById<Button>(R.id.clear_btn)

        // number buttons click listeners
        zeroBtn.setOnClickListener { this.presenter.addChar('0') }
        oneBtn.setOnClickListener { this.presenter.addChar('1') }
        twoBtn.setOnClickListener { this.presenter.addChar('2') }
        threeBtn.setOnClickListener { this.presenter.addChar('3') }
        fourBtn.setOnClickListener { this.presenter.addChar('4') }
        fiveBtn.setOnClickListener { this.presenter.addChar('5') }
        sixBtn.setOnClickListener { this.presenter.addChar('6') }
        sevenBtn.setOnClickListener { this.presenter.addChar('7') }
        eightBtn.setOnClickListener { this.presenter.addChar('8') }
        nineBtn.setOnClickListener { this.presenter.addChar('9') }
        dotBtn.setOnClickListener { this.presenter.addChar('.') }

        // arithmetic buttons click listeners
        plusBtn.setOnClickListener {
            this.presenter.addChar('+')
        }
        minusBtn.setOnClickListener {
            this.presenter.addChar('-')
        }
        divisionBtn.setOnClickListener {
            this.presenter.addChar('/')
        }
        multiplyBtn.setOnClickListener {
            this.presenter.addChar('*')
        }
        percentBtn.setOnClickListener {
            this.presenter.addChar('%')
        }

        backspaceBtn.setOnClickListener {
            this.presenter.removeChar()
        }
        clearBtn.setOnClickListener {
            this.presenter.removerAll()
        }
        calculateBtn.setOnClickListener {
            this.presenter.equal()
        }
    }
}