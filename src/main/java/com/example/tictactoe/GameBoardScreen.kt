package com.example.tictactoe

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
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
    state: GameState,
    onCellClick: (Int, Int) -> Unit,
    onResetClick: () -> Unit, //
    modifier: Modifier = Modifier
) {
    val currentPlayerName = if (state.currentPlayer == Player.X) playerXName else playerOName
    val turnColor = if (state.currentPlayer == Player.X) Color.Blue else Color.Red

    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 32.dp, vertical = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text("X - $playerXName", fontSize = 20.sp, fontWeight = FontWeight.Bold, color = Color.Blue)
            Text("O - $playerOName", fontSize = 20.sp, fontWeight = FontWeight.Bold, color = Color.Red)
        }

        val statusText = when {
            state.winner != null -> "Winner: ${if (state.winner == Player.X) playerXName else playerOName}!"
            state.isDraw -> "It's a Draw!"
            else -> "$currentPlayerName's turn"
        }

        Text(
            text = statusText,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = if (state.isGameOver) Color.Black else turnColor,
            modifier = Modifier.padding(vertical = 16.dp)
        )

        Column(modifier = Modifier.padding(16.dp)) {
            for (row in 0..2) {
                Row {
                    for (col in 0..2) {
                        GameCell(
                            symbol = state.board[row][col], // שולחים לתא מה לצייר
                            onClick = { onCellClick(row, col) } // שולחים לתא את הלחיצה
                        )
                    }
                }
            }
        }

        if (state.isGameOver) {
            Button(
                onClick = onResetClick,
                modifier = Modifier.padding(top = 16.dp)
            ) {
                Text("Play Again")
            }
        }
    }
}

@Composable
fun GameCell(symbol: Player, onClick: () -> Unit) {
    val textSymbol = when (symbol) {
        Player.X -> "X"
        Player.O -> "O"
        Player.NONE -> ""
    }

    val textColor = when (symbol) {
        Player.X -> Color.Blue
        Player.O -> Color.Red
        else -> Color.Black
    }

    Box(
        modifier = Modifier
            .size(100.dp)
            .padding(4.dp)
            .clip(RoundedCornerShape(12.dp))
            .border(2.dp, Color.Black, RoundedCornerShape(12.dp))
            .background(Color.White)
            .clickable { onClick() },
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = textSymbol,
            fontSize = 48.sp,
            fontWeight = FontWeight.Bold,
            color = textColor
        )
    }
}