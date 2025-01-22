import java.util.Arrays;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {
        public static void main(String[] args) {

                int[][] result;

                int[][] board1 = DataParser.parseData("sudoku.csv");
                result = Sudoku.solve(board1);
                DataParser.printBoard(board1);
                DataParser.printBoard(result);

                int[][] board2 = DataParser.parseData("triangle.csv");
                result = Sudoku.solve(board2);
                DataParser.printBoard(board2);
                DataParser.printBoard(result);

                int[][] impossible = DataParser.parseData("impossible.csv");
                result = Sudoku.solve(impossible);
                DataParser.printBoard(result);

                DataParser.writeBoard(result, "solution.csv");
        }
}