import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class SudokuGenerator {

    public static void main(String[] args) {
        int[][][] sudokuPuzzles = generateSudoku();
        printSudokuPuzzles(sudokuPuzzles);
    }

    public static int[][][] generateSudoku() {
        int[][][] sudokuPuzzles = new int[9][9][9];
        ExecutorService executor = Executors.newFixedThreadPool(9);

        for (int i = 0; i < 9; i++) {
            final int puzzleIndex = i;
            executor.submit(() -> generateSudokuPuzzle(sudokuPuzzles, puzzleIndex));
        }

        executor.shutdown();
        try {
            executor.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return sudokuPuzzles;
    }

    public static void generateSudokuPuzzle(int[][][] sudokuPuzzles, int puzzleIndex) {
        int[][] sudoku = new int[9][9];
        fillSudoku(sudoku);
        sudokuPuzzles[puzzleIndex] = sudoku;
    }

    public static void fillSudoku(int[][] sudoku) {
        fillSudoku(sudoku, 0, 0);
    }

    public static boolean fillSudoku(int[][] sudoku, int row, int col) {
        if (row == 9) {
            return true; // Successfully filled the entire grid
        }

        if (col == 9) {
            return fillSudoku(sudoku, row + 1, 0); // Move to the next row
        }

        Random random = new Random();
        int[] values = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        for (int i = 0; i < 9; i++) {
            int index = random.nextInt(9 - i);
            int num = values[index];
            values[index] = values[8 - i];

            if (isValid(sudoku, row, col, num)) {
                sudoku[row][col] = num;
                if (fillSudoku(sudoku, row, col + 1)) {
                    return true;
                }
                sudoku[row][col] = 0; // Backtrack
            }
        }

        return false; // Unable to find a valid number
    }

    public static boolean isValid(int[][] sudoku, int row, int col, int num) {
        // Check row and column
        for (int i = 0; i < 9; i++) {
            if (sudoku[row][i] == num || sudoku[i][col] == num) {
                return false;
            }
        }

        // Check 3x3 subgrid
        int startRow = (row / 3) * 3;
        int startCol = (col / 3) * 3;
        for (int i = startRow; i < startRow + 3; i++) {
            for (int j = startCol; j < startCol + 3; j++) {
                if (sudoku[i][j] == num) {
                    return false;
                }
            }
        }

        return true;
    }

    public static void printSudoku(int[][] sudoku) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print(sudoku[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void printSudokuPuzzles(int[][][] sudokuPuzzles) {
        for (int i = 0; i < 9; i++) {
            System.out.println("Sudoku Puzzle " + (i + 1) + ":");
            printSudoku(sudokuPuzzles[i]);
            System.out.println();
        }
    }
}