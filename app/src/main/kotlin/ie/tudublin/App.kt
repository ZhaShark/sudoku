package ie.tudublin

import java.io.File

fun main(args: Array<String>) {
    if (args.isEmpty()) {
        println("Usage: java -jar sudoku.jar <input_file>")
        return
    }

    val fileName = args[0]
    val board = readBoardFromFile(fileName)

    if (board == null) {
        println("Error: Could not read board from file '$fileName'")
        return
    }

    println("Input board:")
    Sudoku().printBoard(board)
    println()

    val solver = Sudoku()
    val startTime = System.currentTimeMillis()
    val solved = solver.solve(board)
    val endTime = System.currentTimeMillis()

    if (solved) {
        println("Solution:")
        solver.printBoard(board)
        println("\nSolved in ${endTime - startTime} ms")
    } else {
        println("Could not find a solution for this board")
    }
}

fun readBoardFromFile(fileName: String): Array<IntArray>? {
    return try {
        val lines = File(fileName).readLines()
        val board = Array(9) { IntArray(9) }

        for ((row, line) in lines.withIndex()) {
            if (row >= 9) break
            
            val numbers = line.trim().split("\\s+".toRegex())
            for ((col, numStr) in numbers.withIndex()) {
                if (col >= 9) break
                board[row][col] = if (numStr == "." || numStr == "0") 0 else numStr.toInt()
            }
        }
        board
    } catch (e: Exception) {
        null
    }
}