package com.example.tictactoe

import android.R
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tictactoe.ui.theme.ticTacToeColors

@Composable
fun PlayerSelectionScreen(
    onStartGame: (String, String) -> Unit,
    modifier: Modifier = Modifier
) {
    var playerXName by remember { mutableStateOf("") }
    var playerOName by remember { mutableStateOf("") }

    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Choose Players",
            style = MaterialTheme.typography.headlineLarge,
            color = MaterialTheme.colorScheme.onBackground,
            modifier = Modifier.padding(bottom = 48.dp)
        )

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(bottom = 32.dp)
        ) {
            Text(
                text = "X",
                fontSize = 64.sp,
                style = MaterialTheme.typography.headlineLarge.copy(fontSize = 64.sp),  // ✅ Using Typography with custom size
                color = MaterialTheme.ticTacToeColors.playerX,  // ✅ Using Custom Theme Colors
                modifier = Modifier.padding(bottom = 8.dp)
            )
            OutlinedTextField(
                value = playerXName,
                onValueChange = { playerXName = it },
                label = { Text("Player X Name") },
                singleLine = true
            )
        }

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(bottom = 48.dp)
        ) {
            Text(
                text = "O",
                fontSize = 64.sp,
                style = MaterialTheme.typography.headlineLarge.copy(fontSize = 64.sp),
                color = MaterialTheme.ticTacToeColors.playerO,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            OutlinedTextField(
                value = playerOName,
                onValueChange = { playerOName = it },
                label = { Text("Player O Name") },
                singleLine = true
            )
        }

        Button(
            onClick = {
                if (playerXName.isNotEmpty() && playerOName.isNotEmpty()) {
                    onStartGame(playerXName, playerOName)
                }
            },
            enabled = playerXName.isNotEmpty() && playerOName.isNotEmpty()
        ) {
            Text(
                text = "Start Game",
                style = MaterialTheme.typography.labelLarge
            )
        }
    }
}