public class Boards {
    private char[][] boardA;
    private char[][] boardB;
    int n;

    public Boards() {
        n = 11;
        boardA = new char[n][n];
        boardB = new char[n][n];
        initBoardA();
        initBoardB();
    }

    public void initBoardA() {
        boardA[0][0] = ' '; // Top-left corner
        for (int i = 1; i < n; i++) {
            if (i < 10) {
                boardA[0][i] = (char) ('0' + i); // Numbers '1' to '9'
            } else if (i == 10) {
                boardA[0][i] = '*';
            } else {
                boardA[0][i] = (char) ('A' + (i - 10)); // Letters for additional columns
            }
        }

        // Fill first column (row headers)
        for (int i = 1; i < n; i++) {
            boardA[i][0] = (char) ('A' + (i - 1)); // Letters 'A' to 'Z'
        }

        // Fill the rest of the table with '~'
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < n; j++) {
                boardA[i][j] = '~';
            }
        }
    }
    public void initBoardB() {
        boardB[0][0] = ' '; // Top-left corner
        for (int i = 1; i < n; i++) {
            if (i < 10) {
                boardB[0][i] = (char) ('0' + i); // Numbers '1' to '9'
            } else if (i == 10) {
                boardB[0][i] = '*';
            } else {
                boardB[0][i] = (char) ('A' + (i - 10)); // Letters for additional columns
            }
        }

        for (int i = 1; i < n; i++) {
            boardB[i][0] = (char) ('A' + (i - 1)); // Letters 'A' to 'Z'
        }

        for (int i = 1; i < n; i++) {
            for (int j = 1; j < n; j++) {
                boardB[i][j] = '~';
            }
        }
    }

    public void printBoardA() {
        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 11; j++) {
                if (boardA[i][j] == '*') {
                    System.out.print(10);
                } else {
                    System.out.print(boardA[i][j] + " ");
                }
            }
            System.out.println();
        }
        System.out.println();
    }

    public void printBoardB() {
        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 11; j++) {
                if (boardB[i][j] == '*') {
                    System.out.print(10);
                } else {
                    System.out.print(boardB[i][j] + " ");
                }
            }
            System.out.println();
        }
        System.out.println();
    }

    public void placeShip(Ship ship) {
        int x1 = ship.getCoord1().getX();
        int x2 = ship.getCoord2().getX();
        int y1 = ship.getCoord1().getY();
        int y2 = ship.getCoord2().getY();
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
        for (int i = x1; i <= x2; i++) {
            for (int j = y1; j <= y2; j++) {
                boardA[i][j] = 'O';
            }
        }
    }

    public char[][] getBoardA() {
        return boardA;
    }

    public char getBoardAValue(int i, int j) {
        return boardA[i][j];
    }

    public char getBoardBValue(int i, int j) {
        return boardB[i][j];
    }

    public void setBoardBValue(int i, int j, char value){boardB[i][j] = value;}
    public void setBoardAValue(int i, int j, char value){boardA[i][j] = value;}

    public char[][] getBoardB() {
        return boardB;
    }

    public int getN() {
        return n;
    }
}
