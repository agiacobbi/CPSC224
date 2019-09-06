public class Cell {
    private Coordinates coordinates;
    private char symbol;

    public Cell() {
        this.coordinates = new Coordinates();
        this.symbol = '-';
    }

    public Cell(Coordinates coordinates, char symbol) {
        this.coordinates = coordinates;
        this.symbol = symbol;
    }

    public void setCoordinates(int row, int col) {
        this.coordinates.setCoordinate(row, col);
    }

    @Override
    public String toString() {
        return symbol + "";
    }
}
