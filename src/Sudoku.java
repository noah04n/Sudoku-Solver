import java.util.*;

public class Sudoku {
    private int[][] board_values;
    private int[][] boardTranspose;
    private LinkedList<Region> board_regions;
    private int[][][] rowOfRegions;

    private LinkedList<LinkedList<Integer>> rowsList;
    private LinkedList<LinkedList<Integer>> colsList;

    public class Region {
        private LinkedList<Integer> values;
        public Region() {
            this.values = new LinkedList<>();
        }
    }

    public Sudoku(int[][] board) {
        board_values = new int[9][9];
        boardTranspose = transposeSudoku(board);
        board_regions = new LinkedList<>();

        // Copy the board
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                board_values[i][j] = board[i][j];
            }
        }

        // Split the board
        rowOfRegions = new int[3][3][9];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                rowOfRegions[i][j] = board_values[j + i * 3];
            }
        }

        // Create regions
        for (int[][] row : rowOfRegions) {
            for (int i = 0; i < 3; i++) {
                Region curRegion = new Region();
                for (int j = 0; j < 3; j++) {
                    for (int k = 0; k < 3; k++) {
                        curRegion.values.add(row[j][k + i * 3]);
                    }
                }
                board_regions.add(curRegion);
            }
        }

        // Create rows
        rowsList = new LinkedList<>();
        for (int i = 0; i < 9; i++) {
            LinkedList<Integer> row = new LinkedList<>();
            for (int j = 0; j < 9; j++) {
                row.add(board_values[i][j]);
            }
            rowsList.add(row);
        }

        // Create columns
        colsList = new LinkedList<>();
        for (int i = 0; i < 9; i++) {
            LinkedList<Integer> col = new LinkedList<>();
            for (int j = 0; j < 9; j++) {
                col.add(boardTranspose[i][j]);
            }
            colsList.add(col);
        }
    }
    private int[][] transposeSudoku(int[][] matrix){
        int m = matrix.length;
        int n = matrix[0].length;

        int[][] transposedMatrix = new int[n][m];

        for(int x = 0; x < n; x++) {
            for(int y = 0; y < m; y++) {
                transposedMatrix[x][y] = matrix[y][x];
            }
        }

        return transposedMatrix;
    }

    public static int[][] solve(int[][] board) {
        Sudoku sudoku = new Sudoku(board);
        int curRow;
        int curCol;

        for (int i = 0; i < 9; i++) {
            Region curRegion = sudoku.board_regions.get(i);
            for (int j = 0; j < 9; j++) {
                curRow = (i / 3)*3 + (j / 3);
                curCol = (i % 3)*3 + (j % 3);
                int cur = 1;

                // If the current cell is empty
                if (curRegion.values.get(j) == 0) {
                    for (int k = 0; k < 9; k++) {
                        // If placement works, place number and solve recursively
                        if (!curRegion.values.contains(cur) && !sudoku.rowsList.get(curRow).contains(cur) && !sudoku.colsList.get(curCol).contains(cur)) {
                            sudoku.board_values[curRow][curCol] = cur;
                            int[][] result = solve(sudoku.board_values);
                            if (result == null) {
                                cur++;
                            } else {
                                return result;
                            }
                        } else {
                            cur++;
                        }
                    }
                    // If we made it this far no value can be placed
                    return null;
                }

            }
        }
        return sudoku.board_values;
    }

}
