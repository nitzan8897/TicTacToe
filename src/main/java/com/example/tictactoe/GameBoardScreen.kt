package com.example.tictactoe

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun GameBoardScreen(
    playerXName: String,
    playerOName: String,
    modifier: Modifier = Modifier
) {
    // TODO: Your partner will make this dynamic
    val currentTurn = "X"
    val currentPlayerName = if (currentTurn == "X") playerXName else playerOName

    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Player names at top
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 32.dp, vertical = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "X - $playerXName",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Blue
            )
            Text(
                text = "O - $playerOName",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Red
            )
        }

        // Current turn indicator
        Text(
            text = "$currentPlayerName's turn",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = if (currentTurn == "X") Color.Blue else Color.Red,
            modifier = Modifier.padding(vertical = 16.dp)
        )

        // Game Board (3x3 grid)
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            for (row in 0..2) {
                Row {
                    for (col in 0..2) {
                        GameCell()
                    }
                }
            }
        }
    }
}

@Composable
fun GameCell() {
    Box(
        modifier = Modifier
            .size(100.dp)
            .padding(4.dp)
            .clip(RoundedCornerShape(12.dp))
            .border(2.dp, Color.Black, RoundedCornerShape(12.dp))
            .background(Color.White),
        contentAlignment = Alignment.Center
    ) {
        // TODO: Your partner will add X/O display and click logic here
        Text(
            text = "",
            fontSize = 48.sp,
            fontWeight = FontWeight.Bold
        )
    }
}