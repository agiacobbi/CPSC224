import java.awt.*;
import java.util.Arrays;

public class TicTacToeBoard {
    private int dimensions;
    private Cell[][] grid;

    public TicTacToeBoard(int dimensions) {
        this.dimensions = dimensions;
        this.grid = new Cell[dimensions][dimensions];

        for(int i = 0; i < dimensions; i++) {
            for(int j = 0; j < dimensions; j++) {
                Coordinates current = new Coordinates(i, j);
                this.grid[i][j] = new Cell(current, '-');
            }
        }
    }

    @Override
    public String toString() {
        String board = "  ";

        for (int i = 0; i < dimensions; i++) {
            board += i + " ";
        }
        board += '\n';

        for(int j = 0; j < dimensions; j++) {
            board += j + " ";
            for(int k = 0; k < dimensions; k++) {
                board += this.grid[j][k].toString() + " ";
            }
            board += '\n';
        }

        return board;
    }

    public boolean isValidMove() {
        return true;
    }


}
