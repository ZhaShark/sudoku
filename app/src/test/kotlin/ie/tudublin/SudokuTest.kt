package ie.tudublin

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*

class SudokuTest {

    @Test
    fun testEasyBoard() {
        val board = arrayOf(
            intArrayOf(5, 3, 0, 0, 7, 0, 0, 0, 0),
            intArrayOf(6, 0, 0, 1, 9, 5, 0, 0, 0),
            intArrayOf(0, 9, 8, 0, 0, 0, 0, 6, 0),
            intArrayOf(8, 0, 0, 0, 6, 0, 0, 0, 3),
            intArrayOf(4, 0, 0, 8, 0, 3, 0, 0, 1),
            intArrayOf(7, 0, 0, 0, 2, 0, 0, 0, 6),
            intArrayOf(0, 6, 0, 0, 0, 0, 2, 8, 0),
            intArrayOf(0, 0, 0, 4, 1, 9, 0, 0, 5),
            intArrayOf(0, 0, 0, 0, 8, 0, 0, 7, 9)
        )

        val solver = Sudoku()
        val solved = solver.solve(board)

        assertTrue(solved, "Easy board should be solvable")
        if (solved) {
            assertTrue(isValidSolution(board), "Solution should be valid")
        }
    }

    @Test
    fun testAnotherEasyBoard() {
        val board = arrayOf(
            intArrayOf(0, 0, 3, 0, 2, 0, 6, 0, 0),
            intArrayOf(9, 0, 0, 3, 0, 5, 0, 0, 1),
            intArrayOf(0, 0, 1, 8, 0, 6, 4, 0, 0),
            intArrayOf(0, 0, 8, 1, 0, 2, 9, 0, 0),
            intArrayOf(7, 0, 0, 0, 0, 0, 0, 0, 8),
            intArrayOf(0, 0, 6, 7, 0, 8, 2, 0, 0),
            intArrayOf(0, 0, 2, 6, 0, 9, 5, 0, 0),
            intArrayOf(8, 0, 0, 2, 0, 3, 0, 0, 9),
            intArrayOf(0, 0, 5, 0, 1, 0, 3, 0, 0)
        )

        val solver = Sudoku()
        val solved = solver.solve(board)

        assertTrue(solved, "Easy board should be solvable")
        if (solved) {
            assertTrue(isValidSolution(board), "Solution should be valid")
        }
    }

    @Test
    fun testUnsolvableBoard() {
        val board = arrayOf(
            intArrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9),
            intArrayOf(1, 0, 0, 0, 0, 0, 0, 0, 0),
            intArrayOf(0, 0, 0, 0, 0, 0, 0, 0, 0),
            intArrayOf(0, 0, 0, 0, 0, 0, 0, 0, 0),
            intArrayOf(0, 0, 0, 0, 0, 0, 0, 0, 0),
            intArrayOf(0, 0, 0, 0, 0, 0, 0, 0, 0),
            intArrayOf(0, 0, 0, 0, 0, 0, 0, 0, 0),
            intArrayOf(0, 0, 0, 0, 0, 0, 0, 0, 0),
            intArrayOf(0, 0, 0, 0, 0, 0, 0, 0, 0)
        )

        val solver = Sudoku()
        val solved = solver.solve(board)

        assertFalse(solved, "Unsolvable board should return false")
    }

    @Test
    fun testAlmostCompleteBoard() {
        val board = arrayOf(
            intArrayOf(5, 3, 4, 6, 7, 8, 9, 1, 2),
            intArrayOf(6, 7, 2, 1, 9, 5, 3, 4, 8),
            intArrayOf(1, 9, 8, 3, 4, 2, 5, 6, 7),
            intArrayOf(8, 5, 9, 7, 6, 1, 4, 2, 3),
            intArrayOf(4, 2, 6, 8, 5, 3, 7, 9, 1),
            intArrayOf(7, 1, 3, 9, 2, 4, 8, 5, 6),
            intArrayOf(9, 6, 1, 5, 3, 7, 2, 8, 4),
            intArrayOf(2, 8, 7, 4, 1, 9, 6, 3, 5),
            intArrayOf(3, 4, 5, 2, 8, 6, 1, 7, 0) 
        )

        val solver = Sudoku()
        val solved = solver.solve(board)

        assertTrue(solved, "Almost complete board should be solvable")
        if (solved) {
            assertTrue(isValidSolution(board), "Solution should be valid")
        }
    }

    private fun isValidSolution(board: Array<IntArray>): Boolean {
        // Check rows
        for (row in 0 until 9) {
            val seen = BooleanArray(10)
            for (col in 0 until 9) {
                val num = board[row][col]
                if (num < 1 || num > 9 || seen[num]) return false
                seen[num] = true
            }
        }

        // Check columns
        for (col in 0 until 9) {
            val seen = BooleanArray(10)
            for (row in 0 until 9) {
                val num = board[row][col]
                if (num < 1 || num > 9 || seen[num]) return false
                seen[num] = true
            }
        }

        // Check 3x3 boxes
        for (boxRow in 0 until 3) {
            for (boxCol in 0 until 3) {
                val seen = BooleanArray(10)
                for (row in boxRow * 3 until boxRow * 3 + 3) {
                    for (col in boxCol * 3 until boxCol * 3 + 3) {
                        val num = board[row][col]
                        if (num < 1 || num > 9 || seen[num]) return false
                        seen[num] = true
                    }
                }
            }
        }

        return true
    }
}