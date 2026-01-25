package com.example.tictactoe

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

data class GameState(
    val board: List<List<Player>>,
    val currentPlayer: Player,
    val winner: Player?,
    val isDraw: Boolean,
    val isGameOver: Boolean
)

class GameViewModel : ViewModel() {

    private val _gameState = MutableStateFlow(createInitialState())
    val gameState: StateFlow<GameState> = _gameState.asStateFlow()

    fun onCellClicked(row: Int, col: Int) {
        val currentState = _gameState.value
        if (currentState.isGameOver || currentState.board[row][col] != Player.NONE) return

        val newBoard = currentState.board.map { it.toMutableList() }
        newBoard[row][col] = currentState.currentPlayer

        val winner = checkVictory(newBoard)
        val isDraw = winner == null && newBoard.flatten().none { it == Player.NONE }
        val isGameOver = winner != null || isDraw
        val nextPlayer = if (currentState.currentPlayer == Player.X) Player.O else Player.X

        _gameState.value = currentState.copy(
            board = newBoard,
            currentPlayer = if (isGameOver) currentState.currentPlayer else nextPlayer,
            winner = winner,
            isDraw = isDraw,
            isGameOver = isGameOver
        )
    }

    private fun createInitialState(): GameState = GameState(
        board = List(3) { List(3) { Player.NONE } },
        currentPlayer = Player.X,
        winner = null,
        isDraw = false,
        isGameOver = false
    )

    private fun checkRowWinner(board: List<List<Player>>): Player? {
        for (i in 0..2) {
            if (board[i][0] != Player.NONE && board[i][0] == board[i][1] && board[i][1] == board[i][2]) {
                return board[i][0]
            }
        }
        return null
    }

    private fun checkColumnWinner(board: List<List<Player>>): Player? {
        for (i in 0..2) {
            if (board[0][i] != Player.NONE && board[0][i] == board[1][i] && board[1][i] == board[2][i]) {
                return board[0][i]
            }
        }
        return null
    }

    private fun checkDiagonalWinner(board: List<List<Player>>): Player? {
        if (board[0][0] != Player.NONE && board[0][0] == board[1][1] && board[1][1] == board[2][2]) {
            return board[0][0]
        }
        if (board[0][2] != Player.NONE && board[0][2] == board[1][1] && board[1][1] == board[2][0]) {
            return board[0][2]
        }
        return null
    }

        private fun checkVictory(board: List<List<Player>>): Player? {
        return checkRowWinner(board) ?: checkColumnWinner(board) ?: checkDiagonalWinner(board)
    }

        fun resetGame() {
        _gameState.value = createInitialState()
    }
}