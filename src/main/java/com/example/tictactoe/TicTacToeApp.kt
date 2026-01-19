package com.example.tictactoe

import androidx.compose.runtime.*
import androidx.compose.ui.Modifier

@Composable
fun TicTacToeApp(modifier: Modifier = Modifier) {
    var showGameBoard by remember { mutableStateOf(false) }
    var playerXName by remember { mutableStateOf("") }
    var playerOName by remember { mutableStateOf("") }

    if (!showGameBoard) {
        PlayerSelectionScreen(
            onStartGame = { xName, oName ->
                playerXName = xName
                playerOName = oName
                showGameBoard = true
            },
            modifier = modifier
        )
    } else {
        GameBoardScreen(
            playerXName = playerXName,
            playerOName = playerOName,
            modifier = modifier
        )
    }
}