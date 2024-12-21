package core.algorithms;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Для подсчёта количества островов в двумерной сетке можно использовать алгоритм поиска в глубину (DFS) или поиск в ширину (BFS).
 * Основная идея: пройти по каждому элементу матрицы, начиная новый поиск, если элемент равен 1 (часть суши), и увеличивать
 * счётчик островов.
 */
public class NumberOfIslands {

    /**
     * Проходим по каждой клетке сетки.
     * Если находим сушу (1), запускаем DFS/BFS и увеличиваем счётчик островов.
     */
    public static int countIslands(int[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }

        int rows = grid.length;
        int cols = grid[0].length;
        int numberOfIslands = 0;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == 1) { // Начинаем поиск, если находим сушу
                    numberOfIslands++;
                    dfs(grid, i, j);
                }
            }
        }

        return numberOfIslands;
    }

    /**
     * Рекурсивно помечаем текущую клетку и все её соединённые клетки суши как посещённые (0).
     * Каждый раз, когда начинаем новый поиск (находим новую сушу), увеличиваем счётчик.
     */
    private static void dfs(int[][] grid, int row, int col) {
        // Проверяем выход за границы и воду
        if (row < 0 || col < 0 || row >= grid.length || col >= grid[0].length || grid[row][col] == 0) {
            return;
        }

        // Помечаем текущую клетку как посещённую
        grid[row][col] = 0;

        // Рекурсивно вызываем DFS для соседних клеток (вверх, вниз, влево, вправо)
        dfs(grid, row - 1, col); // вверх
        dfs(grid, row + 1, col); // вниз
        dfs(grid, row, col - 1); // влево
        dfs(grid, row, col + 1); // вправо
    }

    /**
     * В очередь добавляется текущая клетка суши.
     * Пока очередь не пуста, обрабатываем текущую клетку и добавляем соседей (сушу) в очередь.
     * Используем массивы rowDir и colDir для определения направлений соседей (вверх, вниз, влево, вправо).
     * Сразу помечаем клетки как посещённые (0), чтобы избежать их повторного добавления в очередь.
     */
    private static void bfs(int[][] grid, int row, int col) {
        // Направления для соседей (вверх, вниз, влево, вправо)
        int[] rowDir = {-1, 1, 0, 0};
        int[] colDir = {0, 0, -1, 1};

        // Очередь для BFS
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{row, col});

        // Помечаем клетку как посещённую
        grid[row][col] = 0;

        // Обрабатываем очередь
        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int currentRow = current[0];
            int currentCol = current[1];

            // Обходим всех соседей
            for (int i = 0; i < 4; i++) {
                int newRow = currentRow + rowDir[i];
                int newCol = currentCol + colDir[i];

                // Проверяем границы и сушу
                if (newRow >= 0 && newCol >= 0 && newRow < grid.length && newCol < grid[0].length && grid[newRow][newCol] == 1) {
                    grid[newRow][newCol] = 0; // Помечаем посещённой
                    queue.add(new int[]{newRow, newCol}); // Добавляем в очередь
                }
            }
        }
    }

    public static int[][] grid1() {
        return new int[][]{
            {0, 1, 0, 0},
            {0, 1, 1, 0},
            {0, 0, 0, 0},
            {0, 0, 0, 1}
        };
    }

    public static int[][] grid2() {
        return new int[][]{
            {0, 1, 0, 0, 0},
            {1, 1, 0, 0, 0},
            {0, 0, 1, 0, 1},
            {0, 0, 0, 1, 1}
        };
    }
}
