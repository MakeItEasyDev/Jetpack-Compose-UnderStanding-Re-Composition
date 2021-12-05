package com.jetpack.recomposition

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jetpack.recomposition.ui.theme.ReCompositionTheme
import kotlin.math.roundToInt

class MainActivity : ComponentActivity() {
    private val viewModel: ReComposeViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ReCompositionTheme {
                Surface(color = MaterialTheme.colors.background) {
                    val state: ReComposeState by viewModel.state.collectAsState()
                    ReCompositionScaffold(
                        state,
                        onValueUpdate = { viewModel.updateSlider(it.roundToInt()) },
                        onButtonClick = { viewModel.updateCounter() }
                    )
                }
            }
        }
    }
}

@Composable
fun ReCompositionScaffold(
    state: ReComposeState,
    onValueUpdate: (Float) -> Unit,
    onButtonClick: () -> Unit
) {
    Scaffold(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colors.primary)
                .height(55.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Jetpack Recomposition",
                color = Color.White,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 12.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Slider(
                value = state.sliderValue.toFloat(),
                onValueChange = onValueUpdate,
                valueRange = 0f..10f,
                steps = 10,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp)
            )

            Spacer(modifier = Modifier.height(10.dp))

            CounterRow(
                counter = state.counter,
                onButtonClick = onButtonClick
            )
        }
    }
}

@Composable
fun CounterRow(
    counter: Int,
    onButtonClick: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = counter.toString(),
            fontSize = 20.sp
        )

        Spacer(modifier = Modifier.height(25.dp))

        Button(
            onClick = onButtonClick
        ) {
            Text(
                text = "Click Me!"
            )
        }
    }
}


















