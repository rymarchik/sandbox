package core.algorithms.leetcode;

/**
 * Implement the Game of Life logic on a 2D grid, where each cell is either alive ('1') or dead ('0').
 * Each cell's next state is determined by the number of live neighbors, following these rules:
 * - Underpopulation: A live cell with fewer than two live neighbors dies.
 * - Survival: A live cell with two or three live neighbors lives on.
 * - Overpopulation: A live cell with more than three live neighbors dies.
 * - Reproduction: A dead cell with exactly three live neighbors becomes a live cell.
 * This implementation works on a flattened string representation of the board, where rows are separated by underscores (_).
 * Example: "010_001_111" represents a 3x3 grid.
 */
public class GameOfLife_289 {

    public static String gameOfLifeStrings(String str) {
        // Split the input string into rows based on underscores
        String[] strArray = str.split("_");
        int rows = strArray.length;
        int cols = strArray[0].length();

        // Create two board representations: one for reading, one for writing
        char[][] originalBoard = new char[rows][cols];
        char[][] copyBoard = new char[rows][cols];

        // Fill both boards with initial state
        for (int i = 0; i < rows; i++) {
            originalBoard[i] = strArray[i].toCharArray();
            copyBoard[i] = strArray[i].toCharArray();
        }

        // Iterate over each cell in the board
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                int liveMates = 0;

                // Check all 8 neighbors
                for (int i = -1; i <= 1; i++) {
                    for (int j = -1; j <= 1; j++) {
                        // Skip the cell itself
                        if (i == 0 && j == 0) {
                            continue;
                        }
                        int rowMate = row + i;
                        int colMate = col + j;

                        // Count valid live neighbors
                        if ((rowMate < rows && rowMate >= 0) && (colMate < cols && colMate >= 0) && copyBoard[rowMate][colMate] == '1') {
                            liveMates++;
                        }
                    }
                }

                // Apply Game of Life rules
                if (copyBoard[row][col] == '0' && liveMates == 3) {
                    // Reproduction
                    originalBoard[row][col] = '1';
                }
                if (copyBoard[row][col] == '1' && (liveMates < 2 || liveMates > 3)) {
                    // Underpopulation or Overpopulation
                    originalBoard[row][col] = '0';
                }
                // Else: cell remains unchanged
            }
        }

        // Convert the updated board back into string format
        for (int i = 0; i < rows; i++) {
            strArray[i] = new String(originalBoard[i]);
        }
        str = String.join("_", strArray);
        return str;
    }
}
