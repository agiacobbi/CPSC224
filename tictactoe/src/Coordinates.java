public class Coordinates {
    private int row;
    private int col;

    public Coordinates() {
        this.row = -1;
        this.col = -1;
    }

    public Coordinates(int row, int col) {
        this.row = row;
        this.col = col;
    }

    @Override
    public String toString() {
        return "(" + row + ", " + col + ")";
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public void setCoordinate(int row, int col) {
        this.row = row;
        this.col = col;
    }

}
