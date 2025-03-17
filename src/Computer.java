import java.util.ArrayList;
import java.util.Random;

public class Computer {
    private Boards boards;
    private Random rand;
    private int[] shipLenghts;
    private ArrayList<Ship> savedShips;

    public Computer() {
        boards = new Boards();
        rand = new Random();
        shipLenghts = new int[]{4, 3, 2, 2, 1};
        savedShips = new ArrayList<>();
        generateShips();
    }

    void generateShips() {
        int x, x2, y, y2;
        for (int i = 0; i < 5; i++) {
            x = rand.nextInt(10) + 1;
            y = rand.nextInt(10) + 1;
            int direction = rand.nextInt(2);
            if (direction == 0) {
                if (x - shipLenghts[i] > 0) {
                    x2 = x - shipLenghts[i];
                } else {
                    x2 = x + shipLenghts[i];
                }
                y2 = y;
            } else {
                if (y - shipLenghts[i] > 0) {
                    y2 = y - shipLenghts[i];
                } else {
                    y2 = y + shipLenghts[i];
                }
                x2 = x;
            }
            Coordinate c1 = new Coordinate(x, y);
            Coordinate c2 = new Coordinate(x2, y2);
            while (!correctCoordinates(c1, c2)) {
                x = rand.nextInt(10) + 1;
                y = rand.nextInt(10) + 1;
                direction = rand.nextInt(2);
                if (direction == 0) {
                    if (x - shipLenghts[i] > 0) {
                        x2 = x - shipLenghts[i];
                    } else {
                        x2 = x + shipLenghts[i];
                    }
                    y2 = y;
                } else {
                    if (y - shipLenghts[i] > 0) {
                        y2 = y - shipLenghts[i];
                    } else {
                        y2 = y + shipLenghts[i];
                    }
                    x2 = x;
                }
                c1 = new Coordinate(x, y);
                c2 = new Coordinate(x2, y2);
            }
            Ship ship = new Ship(c1, c2, shipLenghts[i] + 1);
            placeShip(ship);
            addShip(ship);
        }
    }

    public void placeShip(Ship ship) {boards.placeShip(ship);}

    boolean correctCoordinates(Coordinate c1, Coordinate c2) {
        int x1 = c1.getX();
        int x2 = c2.getX();
        int y1 = c1.getY();
        int y2 = c2.getY();
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
        for (int i = x1 - 1; i <= x2 + 1; i++) {
            for (int j = y1 - 1; j <= y2 + 1; j++) {
                if (i > 0 && i < 11 && j > 0 && j < 11) {
                    if (boards.getBoardAValue(i, j) == 'O') {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public void printBoardA() {boards.printBoardA();}

    public void printBoardB() {boards.printBoardB();}

    public void addShip(Ship ship) {savedShips.add(ship);}

    public char getBoardAValue(int i, int j) {
        return boards.getBoardAValue(i, j);
    }
    public char getBoardBValue(int i, int j) { return boards.getBoardBValue(i, j); }
    public char[][] getBoardA() {return boards.getBoardA();}
    public char[][] getBoardB() {return boards.getBoardB();}
    public int getSize() {
        return boards.getN();
    }
    public void setBoardBValue(int i, int j, char value) {boards.setBoardBValue(i, j, value);}
    public ArrayList<Ship> getSavedShips() {return savedShips;}
    public Ship deleteShip() {return savedShips.removeLast();}
}
