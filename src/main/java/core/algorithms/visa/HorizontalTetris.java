package core.algorithms.visa;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public class HorizontalTetris {

    private static final Map<Character, int[][]> FIGURE_MAP = Map.of(
        'A', new int[][]{{1}},
        'B', new int[][]{{1, 1, 1}},
        'C', new int[][]{{1, 1}, {1, 1}},
        'D', new int[][]{{1, 0}, {1, 1}, {1, 0}},
        'E', new int[][]{{0, 1, 0}, {1, 1, 1}}
    );

    public static int[][] solution(int n, int m, char[] figures) {
        int[][] field = new int[n][m];

        // Итерируем по каждой фигуре в заданной последовательности
        for (int fIndex = 0; fIndex < figures.length; fIndex++) {
            int[][] currentFigure = FIGURE_MAP.get(figures[fIndex]);
            int figureHeight = currentFigure.length;
            int figureWidth = currentFigure[0].length;

            PLACEMENT_SEARCH:
            for (int row = 0; row <= n - figureHeight; row++) {
                for (int column = 0; column <= m - figureWidth; column++) {

                    // 1. Проверка: Можно ли разместить фигуру в (row, column)?
                    if (canFit(field, currentFigure, row, column)) {

                        // 2. Размещение: Если да, заполняем поле
                        placeFigure(field, currentFigure, row, column, fIndex + 1);

                        // 3. Выход: Место найдено, переходим к следующей фигуре
                        break PLACEMENT_SEARCH;
                    }
                }
            }
        }

        Arrays.stream(field)
            .map(row -> Arrays.stream(row) // Преобразование int[] в Stream<String>
                .mapToObj(String::valueOf)
                .collect(Collectors.joining(" "))) // Объединение элементов строки пробелами
            .forEach(System.out::println); // Вывод каждой преобразованной строки
        return field;
    }

    private static boolean canFit(int[][] field, int[][] figure, int startRow, int startCol) {
        int figureHeight = figure.length;
        int figureWidth = figure[0].length;

        for (int i = 0; i < figureHeight; i++) {
            for (int k = 0; k < figureWidth; k++) {
                // Столкновение, если:
                // 1. Текущая ячейка фигуры занята (== 1) И
                // 2. Соответствующая ячейка на поле уже занята (> 0)
                if (figure[i][k] == 1 && field[startRow + i][startCol + k] > 0) {
                    return false;
                }
            }
        }
        return true;
    }

    private static void placeFigure(int[][] field, int[][] figure, int startRow, int startCol, int figureId) {
        int figureHeight = figure.length;
        int figureWidth = figure[0].length;

        for (int i = 0; i < figureHeight; i++) {
            for (int k = 0; k < figureWidth; k++) {
                if (figure[i][k] == 1) {
                    field[startRow + i][startCol + k] = figureId;
                }
            }
        }
    }

    public static void main(String[] args) {
        solution(4, 4, new char[]{'D', 'B', 'A', 'E'});
    }

}
