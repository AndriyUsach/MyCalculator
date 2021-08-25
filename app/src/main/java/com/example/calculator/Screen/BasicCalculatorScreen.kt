package com.example.calculator.Screen

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.calculator.R
import com.example.calculator.Screen.ui.theme.CalculatorTheme
import com.example.calculator.data.SymbolData
import com.example.calculator.fragments.TextCalculatorFieldSettings


@Composable
fun BasicCalculatorScreen(
    expression: String,
    result: String,
    textCalculatorFieldSettings: TextCalculatorFieldSettings,
    numberPainterResourceId: List<Int>,
    operatorPainterResourceId: List<Int>,
    operatorCharList: List<Char>,
    numberCharList: List<Char>,
    ButtonPressed: (Char) -> Unit,
    deleteButtonPressed: () -> Unit,
    clearButtonPressed: () -> Unit,
    equalButtonPressed: () -> Unit
) {
    Scaffold(
        content = {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Bottom
            ) {
                BasicTextCalculatorField(
                    expression = expression,
                    result = result,
                    textCalculatorFieldSettings = textCalculatorFieldSettings
                )

                Divider(
                    color = MaterialTheme.colors.secondary,
                    thickness = 2.dp
                )

                CalculatorButtonGrid(
                    numberPainterResourceId = numberPainterResourceId,
                    numberCharList = numberCharList,
                    operatorPainterResourceId = operatorPainterResourceId,
                    operatorCharList = operatorCharList,
                    buttonPressed = ButtonPressed,
                    equalButtonPressed = equalButtonPressed,
                    deleteButtonPressed = deleteButtonPressed,
                    clearButtonPressed = clearButtonPressed,
                    modifier = Modifier
                        .clip(CircleShape)
                        .background(MaterialTheme.colors.secondary)
                        .size(75.dp)
                )
            }
        }
    )
}

@Preview("Basic Calculator Screen Preview")
@Composable
fun BasicCalculatorScreenPreview() {
    CalculatorTheme {
        BasicCalculatorScreen(
            clearButtonPressed = {},
            deleteButtonPressed = {},
            equalButtonPressed = {},
            ButtonPressed = {},
            expression = "1+1",
            result = "2",
            textCalculatorFieldSettings = TextCalculatorFieldSettings(),
            numberCharList = SymbolData.SymbolCharData.numberSymbolList,
            numberPainterResourceId = SymbolData.SymbolDrawableIdData.numberPainterResourceIdData,
            operatorPainterResourceId = SymbolData.SymbolDrawableIdData.operatorPainterResourceIdData,
            operatorCharList = SymbolData.SymbolCharData.operatorSymbolList
        )
    }
}

@Composable
fun BasicTextCalculatorField(
    modifier: Modifier = Modifier,
    expression: String = "0",
    result: String = "",
    textCalculatorFieldSettings: TextCalculatorFieldSettings
) {
   Column(modifier = modifier
       .fillMaxWidth()
       .padding(start = 15.dp, end = 15.dp)) {

       //field for show expression
       Text(
           text = expression,
           textAlign = TextAlign.End,
           modifier = modifier.fillMaxWidth().animateContentSize(),
           fontSize = textCalculatorFieldSettings.expressionFontSize
       )

       //field for show expression result
       Text(
           text = result,
           textAlign = TextAlign.End,
           modifier = modifier.fillMaxWidth().animateContentSize(),
           fontSize = textCalculatorFieldSettings.resultFontSize
       )
   }
}


@Composable
fun CalculatorButtonGrid(
    numberPainterResourceId: List<Int>,
    numberCharList: List<Char>,
    operatorPainterResourceId: List<Int>,
    operatorCharList: List<Char>,
    buttonPressed: (Char) -> Unit,
    deleteButtonPressed: () -> Unit,
    clearButtonPressed: () -> Unit,
    equalButtonPressed: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(10.dp))
        Spacer(modifier = Modifier.width(15.dp))

        Row {
            IconButton(
                onClick = clearButtonPressed,
                modifier = modifier
            ) {
                Icon(painter = painterResource(id = R.drawable.clear), contentDescription = null)
            }

            Spacer(modifier = Modifier.width(20.dp))
            
            IconButton(
                onClick = deleteButtonPressed,
                modifier = modifier
            ) {
                Icon(painter = painterResource(id = R.drawable.ic_twotone_arrow), contentDescription = null)
            }

            for (i in 0..1) {
                Spacer(modifier = Modifier.width(20.dp))

                IconButton(
                    onClick = { buttonPressed(operatorCharList[i]) },
                    modifier = modifier
                ) {
                    Icon(
                        painter = painterResource(id = operatorPainterResourceId[i]),
                        contentDescription = null
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        Row {
            Column {
                NumberGrid(
                    numberPainterResourceId = numberPainterResourceId,
                    numberCharList = numberCharList,
                    buttonPressed = buttonPressed,
                    modifier = modifier
                )
            }
            
            Column {
                OperatorGrid(
                    operatorPainterResourceId = operatorPainterResourceId
                        .subList(2, operatorPainterResourceId.size),
                    operatorCharList = operatorCharList
                        .subList(2, operatorCharList.size),
                    buttonPressed = buttonPressed,
                    equalButtonPressed = equalButtonPressed,
                    modifier = modifier
                )
            }
        }

        Spacer(modifier = Modifier.width(15.dp))
        Spacer(modifier = Modifier.height(10.dp))
    }
}

@Composable
fun NumberGrid(
    numberPainterResourceId: List<Int>,
    numberCharList: List<Char>,
    buttonPressed: (Char) -> Unit,
    modifier: Modifier = Modifier
) {
    Column {

        for (buttonY in 0..2) {
            val indexId = buttonY * 3
            Row {
                for (buttonX in 0..2) {
                    IconButton(
                        onClick = { buttonPressed(numberCharList[indexId + buttonX])
                                  println(numberCharList[indexId+buttonX])},
                        modifier = modifier
                    ) {
                        Icon(
                            painter = painterResource(id = numberPainterResourceId[indexId + buttonX]),
                            contentDescription = null
                        )
                    }
                    
                    Spacer(modifier = Modifier.width(20.dp))
                }
            }
            Spacer(modifier = Modifier.height(20.dp))
        }
        Row {
            IconButton(
                onClick = { /*TODO*/ },
                modifier = modifier
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_launcher_foreground),
                    contentDescription = null
                )
            }

            Spacer(modifier = Modifier.width(20.dp))


            for (buttonX in 0..1) {
                val indexId = 9
                IconButton(
                    onClick = { buttonPressed(numberCharList[indexId + buttonX]) },
                    modifier = modifier
                ) {
                    Icon(
                        painter = painterResource(id = numberPainterResourceId[indexId + buttonX]),
                        contentDescription = null
                    )
                }

                Spacer(modifier = Modifier.width(20.dp))
            }
        }
    }
}

@Composable
fun OperatorGrid(
    operatorPainterResourceId: List<Int>,
    operatorCharList: List<Char>,
    buttonPressed: (Char) -> Unit,
    equalButtonPressed: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column {
        for (buttonY in 0..2) {
            IconButton(
                onClick = { buttonPressed(operatorCharList[buttonY]) },
                modifier = modifier
            ) {
                Icon(
                    painter = painterResource(id = operatorPainterResourceId[buttonY]),
                    contentDescription = null
                )
            }
            Spacer(modifier = Modifier.height(20.dp))
        }
        //equal button
        IconButton(
                onClick = equalButtonPressed,
            modifier = modifier
        ) {
            Icon(
                painter = painterResource(id = operatorPainterResourceId.last()),
                contentDescription = null
            )
        }
        Spacer(modifier = Modifier.height(20.dp))
    }
}