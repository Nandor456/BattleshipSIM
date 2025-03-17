import java.util.ArrayList;
import java.util.stream.IntStream;

public class Player {
    private Boards boards;
    private int currentShipIndex;
    private int[] shipLengths;
    private ArrayList<Coordinate> coordinates;
    private ArrayList<Ship> savedShips;

    public Player() {
        boards = new Boards();
        currentShipIndex = 0;
        shipLengths = new int[]{5, 4, 3, 3, 2};
        coordinates = new ArrayList<>();
        savedShips = new ArrayList<>();
    }

    public void placeShip(Ship ship) {
        boards.placeShip(ship);
    }

    public char[][] getBoardA() {
        return boards.getBoardA();
    }

    public void printBoardA() {
        boards.printBoardA();
    }

    public void printBoardB() {
        boards.printBoardB();
    }

    public char getBoardAValue(int i, int j) {
        return boards.getBoardAValue(i, j);
    }

    public void setBoardAValue(int i, int j, char value) {
        boards.setBoardAValue(i, j, value);
    }

    public void setBoardBValue(int i, int j, char value) {
        boards.setBoardBValue(i, j, value);
    }

    public char getBoardBValue(int i, int j) {
        return boards.getBoardBValue(i, j);
    }

    public int getSize() {
        return boards.getN();
    }

    public int getCurrentShipIndex() {
        return currentShipIndex;
    }

    public void incCurrentShipIndex() {
        currentShipIndex++;
    }

    public int[] getShipLengths() {
        return shipLengths;
    }

    public ArrayList<Coordinate> getCoordinate() {
        return coordinates;
    }

    public void addCoordinate(Coordinate p) {
        coordinates.add(p);
    }

    public void addShip(Ship ship) {
        savedShips.add(ship);
    }

    public void clearCoordinates() {
        coordinates.clear();
    }

    public ArrayList<Ship> getSavedShips() {
        return savedShips;
    }

    public Ship deleteShip() {
        return savedShips.removeLast();
    }

    public boolean correctShipPlacement(Coordinate start, Coordinate end) {
        int x1 = start.getX();
        int x2 = end.getX();
        int y1 = start.getY();
        int y2 = end.getY();
        if (x1 > x2) {
            int tmp = x1;
            x1 = x2;
            x2 = tmp;
        }
        if (y1 > y2) {
            int tmp = y1;
            y1 = y2;
            y2 = tmp;
        }

        int finalY = y1;
        int finalY1 = y2;
        return IntStream.rangeClosed(x1 - 1, x2 + 1)
                .boxed()
                .flatMap(i -> IntStream.rangeClosed(finalY - 1, finalY1 + 1)
                        .filter(j -> i >= 1 && i <= 10 && j >= 1 && j <= 10)
                        .mapToObj(j -> new int[]{i, j}))
                .noneMatch(coord -> getBoardAValue(coord[0], coord[1]) == 'O');
    }
}
