import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class SetUpPanel extends JFrame {
    private final Player player;
    private final Computer computer;
    private final JPanel panel;
    private final JPanel shipSizePanel;
    private final JPanel panelPlayer;
    private final JPanel panelComputer;
    private final TimerClass gameTimer;
    private final JPanel topPanel;
    private int allSunkComputer;
    private int allSunkPlayer;

    public SetUpPanel() {
        player = new Player();
        computer = new Computer();
        allSunkComputer = 0;
        allSunkPlayer = 0;

        setTitle("Battleship SIM");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setBounds(400, 80, 600, 600);
        setLayout(new BorderLayout());

        panel = new JPanel(new GridLayout(player.getSize(), player.getSize()));
        shipSizePanel = new JPanel();
        panelPlayer = new JPanel(new GridLayout(player.getSize(), player.getSize()));
        panelComputer = new JPanel(new GridLayout(player.getSize(), player.getSize()));
        gameTimer = new TimerClass();
        populateGrid();
        topPanel = new JPanel(new BorderLayout());
        topPanel.add(shipSizePanel, BorderLayout.CENTER);
        topPanel.add(gameTimer.getTimerLabel(), BorderLayout.EAST);
        add(panel, BorderLayout.CENTER);
        add(topPanel, BorderLayout.PAGE_START);
        gameTimer.startTimer();
        setVisible(true);
    }

    private void populateGrid() {
        panel.removeAll();
        shipSizePanel.removeAll();
        int count;
        if (player.getCurrentShipIndex() == 5) {
            count = 2;
        } else {
            count = player.getShipLengths()[player.getCurrentShipIndex()];
        }
        JLabel shipSize = new JLabel("Place a ship of length: " + count);
        shipSizePanel.add(shipSize);
        for (int i = 0; i < player.getSize(); i++) {
            for (int j = 0; j < player.getSize(); j++) {
                char cellValue = player.getBoardAValue(i, j);

                if (cellValue == '~') {
                    JButton button = createButton("/water3.png", i, j);
                    panel.add(button);
                } else if (cellValue == 'O') {
                    JButton button = createButton("/ship.png", i, j);
                    panel.add(button);
                } else if (cellValue == '*') {
                    JLabel label = new JLabel("10", SwingConstants.CENTER);
                    label.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                    panel.add(label);
                } else {
                    JLabel label = new JLabel(String.valueOf(cellValue), SwingConstants.CENTER);
                    label.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                    panel.add(label);
                }
            }
        }
        shipSizePanel.revalidate();
        shipSizePanel.repaint();
        panel.revalidate();
        panel.repaint();
    }

    private void populateFightGrid() {
        panelComputer.removeAll();
        panelPlayer.removeAll();

        for (int i = 0; i < player.getSize(); i++) {
            for (int j = 0; j < player.getSize(); j++) {
                char cellValue = player.getBoardBValue(i, j);

                if (cellValue == '~') {
                    JButton button = createFightButton("/water3.png", i, j);
                    panelPlayer.add(button);
                } else if (cellValue == 'X') {
                    JButton button = createFightButton("/talalt.png", i, j);
                    panelPlayer.add(button);
                } else if (cellValue == '-') {
                    JButton button = createFightButton("/nohit.png", i, j);
                    panelPlayer.add(button);
                } else if (cellValue == 'S') {
                    JButton button = createButton("/sunk.png", i, j);
                    panelPlayer.add(button);
                } else if (cellValue == '*') {
                    JLabel label = new JLabel("10", SwingConstants.CENTER);
                    label.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                    panelPlayer.add(label);
                } else {
                    JLabel label = new JLabel(String.valueOf(cellValue), SwingConstants.CENTER);
                    label.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                    panelPlayer.add(label);
                }
            }
        }
        for (int i = 0; i < computer.getSize(); i++) {
            for (int j = 0; j < computer.getSize(); j++) {
                char cellValue = computer.getBoardBValue(i, j);

                if (cellValue == '~') {
                    JButton button = createFightButton("/water3.png", i, j);
                    panelComputer.add(button);
                } else if (cellValue == 'X') {
                    JButton button = createFightButton("/talalt.png", i, j);
                    panelComputer.add(button);
                } else if (cellValue == '-') {
                    JButton button = createFightButton("/nohit.png", i, j);
                    panelComputer.add(button);
                } else if (cellValue == 'S') {
                    JButton button = createFightButton("/sunk.png", i, j);
                    panelComputer.add(button);
                } else if (cellValue == '*') {
                    JLabel label = new JLabel("10", SwingConstants.CENTER);
                    label.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                    panelComputer.add(label);
                }
                else {
                    JLabel label = new JLabel(String.valueOf(cellValue), SwingConstants.CENTER);
                    label.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                    panelComputer.add(label);
                }
            }
        }
        panelPlayer.revalidate();
        panelPlayer.repaint();
        panelComputer.revalidate();
        panelComputer.repaint();
    }

    private JButton createButton(String iconPath, int i, int j) {
        Icon icon = new ImageIcon(Objects.requireNonNull(getClass().getResource(iconPath)));
        JButton button = new JButton(icon);
        button.addActionListener(new ButtonListener(i, j, button));
        return button;
    }

    private JButton createFightButton(String iconPath, int i, int j) {
        Icon icon = new ImageIcon(Objects.requireNonNull(getClass().getResource(iconPath)));
        JButton button = new JButton(icon);
        button.addActionListener(new ButtonFightListener(i, j, button));
        return button;
    }

    private class ButtonListener implements ActionListener {
        private final int i;
        private final int j;

        public ButtonListener(int i, int j, JButton button) {
            this.i = i;
            this.j = j;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (player.getCurrentShipIndex() >= player.getShipLengths().length) {
                JOptionPane.showMessageDialog(null, "All ships have been placed for Player!");
                return;
            }
            if (player.getCoordinate().size() < 2) {
                player.addCoordinate(new Coordinate(i, j));
            }
            if (player.getCoordinate().size() == 2) {
                Coordinate c1 = player.getCoordinate().get(0);
                Coordinate c2 = player.getCoordinate().get(1);
                int length = calculateShipLength(c1, c2);
                if (length == player.getShipLengths()[player.getCurrentShipIndex()]) {
                    if (!player.correctShipPlacement(c1, c2)) {
                        JOptionPane.showMessageDialog(null, "Invalid placement: too close to another ship!" );
                        player.clearCoordinates();
                        return;
                    }
                    Ship ship = new Ship(c1, c2, length);
                    player.placeShip(ship);
                    player.addShip(ship);
                    SoundPlayer.playSound("shipSound2.wav");
                    player.clearCoordinates();
                    player.incCurrentShipIndex();
                    populateGrid();
                    if (player.getCurrentShipIndex() >= player.getShipLengths().length) {
                        JOptionPane.showMessageDialog(null, "All ships have been placed for Player!");
                        setBounds(200, 80, 1200, 600);
                        fights();
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid ship length! Expected length: " + player.getShipLengths()[player.getCurrentShipIndex()]);
                    player.clearCoordinates();
                }
            }
        }
    }

    private class ButtonFightListener implements ActionListener {
        private final int i;
        private final int j;

        public ButtonFightListener(int i, int j, JButton button) {
            this.i = i;
            this.j = j;
        }
        @Override
        public void actionPerformed(ActionEvent e) {
            if (allSunkComputer == 5) {
                dispose();
            }
            if (allSunkPlayer == 5) {
                JOptionPane.showMessageDialog(null, "You have been defeated!");
                return;
            }
            if (computer.getBoardBValue(i, j) != '~') {
                JOptionPane.showMessageDialog(null, "Choose another cell!");
                return;
            }
            Fight fight = new Fight(player, computer);
            char playerTip = computer.getBoardAValue(i, j);
            if (playerTip == 'O') {
                computer.setBoardBValue(i, j, 'X');
                if (fight.isSunkComputer(new Coordinate(i, j))) {
                    Ship sunk = computer.deleteShip();
                    markAsSunkComputer(sunk);
                    allSunkComputer++;
                }
            } else {
                computer.setBoardBValue(i, j, '-');
            }
            Coordinate randomshot = fight.randomShot();
            if (player.getBoardAValue(randomshot.getX(), randomshot.getY()) == 'O') {
                player.setBoardBValue(randomshot.getX(), randomshot.getY(), 'X');
                if (fight.isSunkPlayer(new Coordinate(randomshot.getX(), randomshot.getY()))) {
                    Ship sunk = player.deleteShip();
                    markAsSunkPlayer(sunk);
                    allSunkPlayer++;
                }
            } else {
                player.setBoardBValue(randomshot.getX(), randomshot.getY(), '-');
            }
            populateFightGrid();
            if (allSunkComputer == 5) {
                gameTimer.stopTimer();
                Integer elapsedTime = gameTimer.getElapsedTime();
                dispose();
                new WinnerPanel(elapsedTime);
            }
            if (allSunkPlayer == 5) {
                gameTimer.stopTimer();
                Integer elapsedTime = gameTimer.getElapsedTime();
                dispose();
                new DefeatPanel(elapsedTime);
            }
        }
    }

    private void fights() {
        panel.removeAll();
        topPanel.removeAll();
        gameTimer.getTimerLabel().removeAll();
        for (int i = 0; i < player.getSize(); i++) {
            for (int j = 0; j < player.getSize(); j++) {
                char cellValue = player.getBoardAValue(i, j);

                if (cellValue == '~') {
                    JButton button = createFightButton("/water3.png", i, j);
                    panelPlayer.add(button);
                } else if (cellValue == 'O') {
                    JButton button = createFightButton("/ship.png", i, j);
                    panelPlayer.add(button);
                } else if (cellValue == '*') {
                    JLabel label = new JLabel("10", SwingConstants.CENTER);
                    label.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                    panelPlayer.add(label);
                }
                else {
                    JLabel label = new JLabel(String.valueOf(cellValue), SwingConstants.CENTER);
                    label.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                    panelPlayer.add(label);
                }
            }
        }
        for (int i = 0; i < computer.getSize(); i++) {
            for (int j = 0; j < computer.getSize(); j++) {
                char cellValue = computer.getBoardBValue(i, j);

                if (cellValue == '~') {
                    JButton button = createFightButton("/water3.png", i, j);
                    panelComputer.add(button);
                } else if (cellValue == '*') {
                    JLabel label = new JLabel("10", SwingConstants.CENTER);
                    label.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                    panelComputer.add(label);
                }
                else {
                    JLabel label = new JLabel(String.valueOf(cellValue), SwingConstants.CENTER);
                    label.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                    panelComputer.add(label);
                }
            }
        }
        remove(topPanel);
        remove(panel);
        gameTimer.getTimerLabel().setFont(new Font("Arial", Font.BOLD, 24));
        gameTimer.resetTimer();
        gameTimer.startTimer();
        gameTimer.getTimerLabel().setAlignmentX(Component.CENTER_ALIGNMENT);
        topPanel.add(gameTimer.getTimerLabel());
        panel.setLayout(new GridLayout(1, 2));
        panel.add(panelPlayer);
        panel.add(panelComputer);
        add(topPanel, BorderLayout.PAGE_START);
        add(panel);
        setVisible(true);
    }

    private void markAsSunkComputer(Ship ship) {
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
        for (int j = x1 - 1; j <= x2 + 1; j++) {
            for (int z = y1 - 1; z <= y2 + 1; z++) {
                if (j >= 1 && j <= 10 && z >= 1 && z <= 10) {
                    computer.setBoardBValue(j, z, '-');
                }
            }
        }
        for (int j = x1; j <= x2; j++) {
            for (int z = y1; z <= y2; z++) {
                computer.setBoardBValue(j, z, 'S');
            }
        }
    }

    private void markAsSunkPlayer(Ship ship) {
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
        for (int j = x1 - 1; j <= x2 + 1; j++) {
            for (int z = y1 - 1; z <= y2 + 1; z++) {
                if (j >= 1 && j <= 10 && z >= 1 && z <= 10) {
                    player.setBoardBValue(j, z, '-');
                }
            }
        }
        for (int j = x1; j <= x2; j++) {
            for (int z = y1; z <= y2; z++) {
                player.setBoardBValue(j, z, 'S');
            }
        }
    }

    private int calculateShipLength(Coordinate c1, Coordinate c2) {
        if (c1.getX() == c2.getX()) {
            return Math.abs(c2.getY() - c1.getY()) + 1;
        } else if (c1.getY() == c2.getY()) {
            return Math.abs(c2.getX() - c1.getX()) + 1;
        } else {
            return -1;
        }
    }
}