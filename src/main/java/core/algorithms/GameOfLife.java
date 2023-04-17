package core.algorithms;

public class GameOfLife {

    public static String gameOfLifeStrings(String str) {
        String[] strArray = str.split("_");
        int rows = strArray.length;
        int cols = strArray[0].length();

        char[][] originalBoard = new char[rows][cols];
        char[][] copyBoard = new char[rows][cols];

        for (int i = 0; i < rows; i++) {
            originalBoard[i] = strArray[i].toCharArray();
            copyBoard[i] = strArray[i].toCharArray();
        }

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                int liveMates = 0;

                for (int i = -1; i <= 1; i++) {
                    for (int j = -1; j <= 1; j++) {
                        if (i == 0 && j == 0) {
                            continue;
                        }
                        int rowMate = row + i;
                        int colMate = col + j;

                        if ((rowMate < rows && rowMate >= 0) && (colMate < cols && colMate >= 0) && copyBoard[rowMate][colMate] == '1') {
                            liveMates++;
                        }
                    }
                }

                if (copyBoard[row][col] == '0' && liveMates == 3) {
                    originalBoard[row][col] = '1';
                }
                if (copyBoard[row][col] == '1' && (liveMates < 2 || liveMates > 3)) {
                    originalBoard[row][col] = '0';
                }
            }
        }

        for (int i = 0; i < rows; i++) {
            strArray[i] = new String(originalBoard[i]);
        }
        str = String.join("_", strArray);
        return str;
    }
}
