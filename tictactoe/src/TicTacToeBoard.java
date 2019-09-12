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

    public boolean isValidMove(Coordinates coordinates) {
        int x = coordinates.getRow();
        int y = coordinates.getCol();

        if (grid[x][y].getSymbol() == '-') {
            return true;
        }

        return false;
    }

    public void makeMove(Coordinates coordinates, char playerSymbol) {
        int x = coordinates.getRow();
        int y = coordinates.getCol();

        grid[x][y].setSymbol(playerSymbol);
    }

    public boolean isWinner(char playerSymbol) {

        //check columns
        for (int i = 0; i < dimensions; i++) {
            int charsInARow = 0;
            for (int j = 0; j < dimensions; j++) {
                if (grid[i][j].getSymbol() == playerSymbol) {
                    charsInARow++;
                }
            }
            if (charsInARow == dimensions) {
                return true;
            }
        }

        //check rows
        for (int j = 0; j < dimensions; j++) {
            int charsInARow = 0;
            for (int i = 0; i < dimensions; i++) {
                if (grid[i][j].getSymbol() == playerSymbol) {
                    charsInARow++;
                }
            }
            if (charsInARow == dimensions) {
                return true;
            }
        }


        //check diagonal
        int diagonalChars = 0;
        for (int i = 0, j = 0; i < dimensions; i++, j++) {
            if (grid[i][j].getSymbol() == playerSymbol) {
                diagonalChars++;
            }
        }
        if (diagonalChars == dimensions) {
            return true;
        }

        //check diagonal
        diagonalChars = 0;
        for (int i = dimensions - 1, j = dimensions - 1; i >= 0; i--, j--) {
            if (grid[i][j].getSymbol() == playerSymbol) {
                diagonalChars++;
            }
        }
        if (diagonalChars == dimensions) {
            return true;
        }

        return false;
    }

    public boolean isFull() {
        for (Cell[] column : grid) {
            for (Cell cell : column) {
                if (cell.getSymbol() == '-') {
                    return false;
                }
            }
        }

        return true;
    }
}
