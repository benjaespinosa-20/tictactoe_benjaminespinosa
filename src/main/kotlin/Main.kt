import java.util.* //libreria para ingresar datos por teclado

private var board = Board(0)
private val player1 = "X"
private val player2 = "O"
private var currentPlayer = ""
private var answer = ""

//funcion principal para llamar a todas las funciones
fun main() {
    println("Ingresa el tamaño de tu tablero")
    val scanner = Scanner(System.`in`) //variable de tipo Scanner creada para ingresar datos por teclado
    val size = Integer.parseInt(scanner.nextLine())

    board = Board(size)
    board.printBoard()

    while (!board.isGameOver) {
        takeTurns()
        println("Turno de la $currentPlayer")
        println("Ingresa el numero de la fila:")
        var row = Integer.parseInt(scanner.nextLine())
        println("ingresa el numero de columna:")
        var col = Integer.parseInt(scanner.nextLine())
        board.placePiece(row - 1, col - 1, currentPlayer)
        if (board.isGameOver) {
            println("¿Quieres jugar de nuevo? escribe 's' o 'si'")
             answer = scanner.nextLine()
            if (isPlayingAgain(answer)) {
                board.resetGame()
            }
        }
    }
}
//funcion para dar los turnos
fun takeTurns() {
    currentPlayer = if (player1 == currentPlayer) {
        player2
    } else {
        player1
    }
}
//funcion que pregunta si quiere jugar de nuevo
fun isPlayingAgain(answer: String): Boolean {
    return (answer.equals("s", ignoreCase = true)
            || answer.equals("si", ignoreCase = true))
}