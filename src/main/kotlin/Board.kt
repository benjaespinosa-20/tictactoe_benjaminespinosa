import kotlin.math.pow //libreria para usar la funcion matematica pow

//clase tablero
class Board(private val n: Int) {

    private val empty = "___"
    private var moveCount = 0
    var isGameOver = false
    private var board = Array(n) { Array(n) {empty} }

    fun resetBoard() {
        board = Array(n) { Array(n) {empty} }
    }
//funcion que imprime tablero
    fun printBoard() {
        board.forEach { row -> row.forEach { element ->
                if (element == empty) {
                    print("|$element|")
                } else {
                    print("| $element |")
                }
            }
            println()
        }
        println()
    }

    //funcion de ganador o perdedor
    fun placePiece(x: Int, y: Int, move: String) {
        if (!isGameOver
            && isPositionValid(x, y)
            && board[x][y] == empty) {
            moveCount++
            board[x][y] = move
            printBoard()
            isGameOver = isWinningMove(x, y, move) || isDraw()
            if (isGameOver && !isDraw()) {
                println("Hay un ganador!")
            } else if (isDraw()) {
                println("Hay un Perdedor!")
            }
        }
    }

    //funcion que valida posicion
    private fun isPositionValid(x: Int, y: Int): Boolean {
        return ((x in 0 until n) && (y in 0 until n))
    }

    //funcion que comprueba opciones para ganar
    private fun isWinningMove(x: Int, y: Int, move: String): Boolean {

        // comprobar la fila
        for (i in 0 until n) {
            if (board[x][i] != move) {
                break
            }
            if (i == n - 1) {
                return true
            }
        }

        // comprobar la columna
        for (i in 0 until n) {
            if (board[i][y] != move) {
                break
            }
            if (i == n - 1) {
                return true
            }
        }

        // comprobar la diagonal
        if (x == y) {
            for (i in 0 until n) {
                if (board[i][i] != move) {
                    break
                }
                if (i == n - 1) {
                    return true
                }
            }
        }

        // comprobar la diagonal inversa
        if (x + y == n - 1) {
            for (i in 0 until n) {
                if (board[i][(n - 1) - i] != move) {
                    break
                }
                if (i == n - 1) {
                    return true
                }
            }
        }
        return false
    }

    fun isDraw(): Boolean {
        return (moveCount == (n.toDouble().pow(2) - 1).toInt())
    }

    //funcion para jugar de nuevo
    fun resetGame() {
        resetBoard()
        isGameOver = false
        moveCount = 0
    }
}