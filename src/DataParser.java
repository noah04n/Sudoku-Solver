import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class DataParser {

    // Configured for 9x9 sudoku
    final static int size = 9;

    public static final String RESET = "\u001B[0m";
    public static final String GREEN = "\u001B[32m";
    public static int[][] parseData(String filePath) {

        int[][] board = new int[size][size];
        String line;
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            int i = 0;
            while ((line = reader.readLine()) != null) {
                String[] nums = line.split(",",-1);
                for (int j = 0; j < 9; j++) {
                        board[i][j] = DataParser.toInt(nums[j]);
                }
                i++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return board;
    }

    public static void printBoard(int[][] board) {
        for (int i = 0; i < size; i++) {
            if (i%3 == 0) {
                System.out.println(GREEN + " -----------------------" + RESET);
            }
            for (int j = 0; j < size; j++) {
                if (j%3 == 0) {
                    System.out.print(GREEN + "| " + RESET);
                }
                if (board[i][j] == 0) {
                    System.out.print("  ");
                } else {
                    System.out.print(board[i][j] + " ");
                }
            }
            System.out.print(GREEN + "|\n" + RESET);
        }
        System.out.println(GREEN + " -----------------------" + RESET);
    }

    private static int toInt(String num) {
        // Remove BOM
        num = num.replace("\uFEFF", "");
        if (num.isEmpty()) {
            return 0;
        } else {
            return Integer.parseInt(num);
        }
    }
}
