package com.example.calculator.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.calculator.R
import com.example.calculator.`interface`.TextResultInterface


class TextResultFragment : Fragment(R.layout.fragment_text_result), TextResultInterface {
    private lateinit var expressionView: TextView
    private lateinit var resultView: TextView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        expressionView = requireView().findViewById(R.id.expression_text_view)
        resultView = requireView().findViewById(R.id.result_text_view)
    }

    override fun changeTextSizeResultView(sp: Float) {
        resultView.textSize = sp
    }

    override fun changeTextSizeExpressionView(sp: Float) {
        expressionView.textSize = sp
    }

    @SuppressLint("SetTextI18n")
    override fun changeTextResultView(result: String) {
        resultView.text = "=$result"
    }

    override fun changeTextExpressionView(expression: String) {
        expressionView.text = expression
    }

    override fun changeColorTextResultView(color: Int) {
        resultView.setTextColor(color)
    }

    override fun changeColorTexExpressionView(color: Int) {
        expressionView.setTextColor(color)
    }

}