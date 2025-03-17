import java.util.Random;

public class Fight {
    private Player player;
    private Computer computer;
    private Random rand;
    private int wholeShip;

    public Fight(Player player, Computer computer) {
        this.player = player;
        this.computer = computer;
        rand = new Random();
        wholeShip = 0;
    }
    public void printBoards() {
        System.out.println("PLAYER: ");
        player.printBoardA();
        player.printBoardB();
        System.out.println("COMPUTER: ");
        computer.printBoardA();
        computer.printBoardB();
    }

    public Coordinate randomShot() {
        int x = rand.nextInt(10) + 1;
        int y = rand.nextInt(10) + 1;
        while (player.getBoardBValue(x, y) != '~'){
            x = rand.nextInt(10) + 1;
            y = rand.nextInt(10) + 1;
        }
        return new Coordinate(x,y);
    }

    boolean isSunkComputer(Coordinate shot) {
        for (Ship i: computer.getSavedShips()) {
            Coordinate start = i.getCoord1();
            Coordinate end = i.getCoord2();
            int x1 = start.getX();
            int y1 = start.getY();
            int x2 = end.getX();
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
            if ((x1 == x2 && shot.getX() == x2 && shot.getY() >= y1 && shot.getY() <= y2) || (y1 == y2 && shot.getY() == y2 && shot.getX() >= x1 && shot.getX() <= x2)) { //Az i hajot talaltuk el
                for (int j = x1; j <= x2; j++) {
                    for (int z = y1; z <= y2; z++) {
                        if (computer.getBoardBValue(j, z) == 'X' && computer.getBoardAValue(j, z) == 'O') {
                            wholeShip++;
                        }
                    }
                }
                if (wholeShip == i.getLength()) {
                    computer.addShip(i);
                }
                return wholeShip == i.getLength();
            }
        }
        return false;
    }
    boolean isSunkPlayer(Coordinate shot) {
        for (Ship i: player.getSavedShips()) {
            Coordinate start = i.getCoord1();
            Coordinate end = i.getCoord2();
            int x1 = start.getX();
            int y1 = start.getY();
            int x2 = end.getX();
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
            if ((x1 == x2 && shot.getX() == x2 && shot.getY() >= y1 && shot.getY() <= y2) || (y1 == y2 && shot.getY() == y2 && shot.getX() >= x1 && shot.getX() <= x2)) { //Az i hajot talaltuk el
                for (int j = x1; j <= x2; j++) {
                    for (int z = y1; z <= y2; z++) {
                        if (player.getBoardBValue(j, z) == 'X' && player.getBoardAValue(j, z) == 'O') {
                            wholeShip++;
                        }
                    }
                }
                if (wholeShip == i.getLength()) {
                    player.addShip(i);
                }
                return wholeShip == i.getLength();
            }
        }
        return false;
    }
}
