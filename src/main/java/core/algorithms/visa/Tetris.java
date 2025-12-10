package core.algorithms.visa;

public class Tetris {

    public static int solution(int[][] field, int[][] figure) {
        int height = field.length;
        int width = field[0].length;
        int figureSize = figure.length;

        for (int column = 0; column <= width - figureSize; column++) {
            int row = 1;

            while (row < height - figureSize + 1) {
                boolean canFit = true;

                for (int dx = 0; dx < figureSize; dx++) {
                    for (int dy = 0; dy < figureSize; dy++) {
                        if (field[row + dx][column + dy] == 1 &&
                            figure[dx][dy] == 1) {
                            canFit = false;
                        }
                    }
                }

                if (!canFit) {
                    break;
                }

                row++;
            }

            row--;

            // Проверяем заполнение только тех строк, которые затронуты фигурой
            for (int dx = 0; dx < figureSize; dx++) {
                int currentRow = row + dx;
                boolean rowFilled = true;

                // 1. Проходим по ВСЕЙ ширине поля
                for (int columnIndex = 0; columnIndex < width; columnIndex++) {

                    boolean isFigureHere =
                        column <= columnIndex &&
                            columnIndex < column + figureSize &&
                            figure[dx][columnIndex - column] == 1;

                    // Ячейка должна быть заполнена ИЛИ в поле (field[currentRow][columnIndex] == 1)
                    // ИЛИ в фигуре (isFigureHere == true).
                    if (field[currentRow][columnIndex] == 0 && !isFigureHere) {
                        // Если в этой ячейке нет ни существующей части поля (0),
                        // ни части размещенной фигуры (false), то строка не заполнена.
                        rowFilled = false;
                        break;
                    }
                }

                if (rowFilled) {
                    return column;
                }
            }
        }

        return -1;
    }

    public static void main(String[] args) {
        int[][] field = {
            {0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 1},
            {1, 1, 0, 1, 0, 1},
            {1, 1, 0, 1, 1, 1}
        };

        int[][] figure = {
            {1, 1, 1},
            {1, 0, 1},
            {1, 0, 0}
        };

        System.out.println(solution(field, figure));
    }
}
