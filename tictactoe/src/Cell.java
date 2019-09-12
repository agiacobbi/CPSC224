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

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public char getSymbol() {
        return symbol;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public void setSymbol(char symbol) {
        this.symbol = symbol;
    }


    @Override
    public String toString() {
        return symbol + "";
    }
}
