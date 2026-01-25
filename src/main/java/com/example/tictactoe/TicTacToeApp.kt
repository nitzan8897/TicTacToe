package com.example.tictactoe

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun TicTacToeApp(modifier: Modifier = Modifier) {
    val viewModel: GameViewModel = viewModel()
    val gameState by viewModel.gameState.collectAsState()

    var showGameBoard by remember { mutableStateOf(false) }
    var playerXName by remember { mutableStateOf("") }
    var playerOName by remember { mutableStateOf("") }

    if (!showGameBoard) {
        PlayerSelectionScreen(
            onStartGame = { xName, oName ->
                playerXName = xName
                playerOName = oName
                viewModel.resetGame()
                showGameBoard = true
            },
            modifier = modifier
        )
    } else {
        GameBoardScreen(
            playerXName = playerXName,
            playerOName = playerOName,
            state = gameState,
            onCellClick = { row, col -> viewModel.onCellClicked(row, col) },
            onResetClick = { viewModel.resetGame() },
            modifier = modifier
        )
    }
}