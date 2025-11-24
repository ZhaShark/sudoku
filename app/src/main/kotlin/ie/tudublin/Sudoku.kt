package ie.tudublin

class Sudoku {
    private var iterations = 0
    private val maxIterations = 2_000_000

    fun solve(board: Array<IntArray>): Boolean {
        iterations = 0
        return solveSudoku(board)
    }

    private fun solveSudoku(board: Array<IntArray>): Boolean {
        iterations++
        if (iterations > maxIterations) {
            return false
        }

        for (row in 0 until 9) {
            for (col in 0 until 9) {
                if (board[row][col] == 0) {
                    for (num in 1..9) {
                        if (isValid(board, row, col, num)) {
                            board[row][col] = num
                            
                            if (solveSudoku(board)) {
                                return true
                            }
                            
                            board[row][col] = 0
                        }
                    }
                    return false
                }
            }
        }
        return true
    }

    private fun isValid(board: Array<IntArray>, row: Int, col: Int, num: Int): Boolean {
        // Check row
        for (x in 0 until 9) {
            if (board[row][x] == num) {
                return false
            }
        }

        // Check column
        for (x in 0 until 9) {
            if (board[x][col] == num) {
                return false
            }
        }

        // Check 3x3 box
        val startRow = row - row % 3
        val startCol = col - col % 3
        for (i in 0 until 3) {
            for (j in 0 until 3) {
                if (board[i + startRow][j + startCol] == num) {
                    return false
                }
            }
        }

        return true
    }

    fun printBoard(board: Array<IntArray>) {
        for (i in 0 until 9) {
            if (i % 3 == 0 && i != 0) {
                println("------+-------+------")
            }
            for (j in 0 until 9) {
                if (j % 3 == 0 && j != 0) {
                    print("| ")
                }
                print(if (board[i][j] == 0) "." else board[i][j].toString())
                print(" ")
            }
            println()
        }
    }
}