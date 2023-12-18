package com.example.sumcalculator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.sumcalculator.ui.theme.SumCalculatorTheme
import java.text.NumberFormat

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SumCalculatorTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    SumCalculatorLayout()
                }
            }
        }
    }
}

@Composable
fun SumCalculatorLayout() {

    var firstNumberInput by remember {
        mutableStateOf("")
    }
    var secondNumberInput by remember {
        mutableStateOf("")
    }

    val firstNumber = firstNumberInput.toDoubleOrNull() ?: 0.0
    val secondNumber = secondNumberInput.toDoubleOrNull() ?: 0.0

    var sum by remember {
        mutableStateOf("")
    }
    Column(
        modifier = Modifier
            .statusBarsPadding()
            .padding(horizontal = 20.dp)
            .safeDrawingPadding(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Calculate the sum of two numbers",
            modifier = Modifier
                .padding(bottom = 16.dp, top = 40.dp)
                .align(alignment = Alignment.Start))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            EditNumberField(value = firstNumberInput,
                onValueChange = {firstNumberInput = it},
                modifier = Modifier
                    .padding(bottom = 32.dp)
                    .weight(0.4F, false))

            Spacer(modifier = Modifier
                .padding(bottom = 32.dp)
                .weight(0.2F, false))

            EditNumberField(value = secondNumberInput,
                onValueChange = {secondNumberInput = it},
                modifier = Modifier
                    .padding(bottom = 32.dp)
                    .weight(0.4F, false))
        }

        Button(onClick = { sum = calculateSum(firstNumber, secondNumber) }) {
            Text(text = "Calculate")
        }

        Text(text = stringResource(R.string.the_result_is, sum),
            style = MaterialTheme.typography.displaySmall)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditNumberField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier) {

    TextField(value = value,
        onValueChange = onValueChange,
        modifier = modifier,
        label = { Text(text = "") },
        singleLine = true,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
    )
}

private fun calculateSum(firstNumber: Double, secondNumber: Double): String {
    val sum = firstNumber + secondNumber
    return NumberFormat.getNumberInstance().format(sum)
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    SumCalculatorTheme {
        SumCalculatorLayout()
    }
}