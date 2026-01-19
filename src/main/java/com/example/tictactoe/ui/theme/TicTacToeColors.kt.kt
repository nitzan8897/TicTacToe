package com.example.tictactoe.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.ui.graphics.Color

data class TicTacToeColors(
    val playerX: Color,
    val playerO: Color,
    val cellBackground: Color,
    val borderColor: Color,
)

val AppTicTacToeColors = TicTacToeColors(
    playerX = PlayerXColor,
    playerO = PlayerOColor,
    cellBackground = CellBackground,
    borderColor = BorderColor,
)

val MaterialTheme.ticTacToeColors: TicTacToeColors
    @Composable
    @ReadOnlyComposable
    get() = AppTicTacToeColors