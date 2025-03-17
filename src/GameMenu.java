import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class GameMenu extends JFrame {
    public GameMenu() {
        // Set up the JFrame
        setTitle("Battleship SIM - Menu");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setBounds(400, 100, 800, 600);

        // Use CardLayout for the main panel
        CardLayout cardLayout = new CardLayout();
        JPanel backgroundPanel = new JPanel(cardLayout);

        // Day panel with background
        JPanel dayPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                try {
                    ImageIcon backgroundImage = new ImageIcon(Objects.requireNonNull(getClass().getResource("/background2.png")));
                    g.drawImage(backgroundImage.getImage(), 0, 0, getWidth(), getHeight(), this);
                } catch (Exception e) {
                    System.err.println("Day background image not found!");
                }
            }
        };
        setupDayPanel(dayPanel, cardLayout, backgroundPanel);

        // Night panel with background
        JPanel nightPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                try {
                    ImageIcon backgroundImage = new ImageIcon(Objects.requireNonNull(getClass().getResource("/background_night.png")));
                    g.drawImage(backgroundImage.getImage(), 0, 0, getWidth(), getHeight(), this);
                } catch (Exception e) {
                    System.err.println("Night background image not found!");
                }
            }
        };
        setupNightPanel(nightPanel, cardLayout, backgroundPanel);

        // Add panels to the CardLayout
        backgroundPanel.add(dayPanel, "Day");
        backgroundPanel.add(nightPanel, "Night");

        // Show the JFrame
        add(backgroundPanel);
        setVisible(true);
    }

    private void setupDayPanel(JPanel dayPanel, CardLayout cardLayout, JPanel backgroundPanel) {
        dayPanel.setLayout(new BorderLayout());

        JLabel label = new JLabel("BATTLESHIP SIMULATOR - Day");
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 32));
        label.setForeground(Color.WHITE);

        JButton switchToNightButton = new JButton("Switch to Night");
        switchToNightButton.addActionListener(e -> cardLayout.show(backgroundPanel, "Night"));

        JPanel buttonPanel = new JPanel();
        buttonPanel.setOpaque(false);
        buttonPanel.add(switchToNightButton);

        JButton startButton = new JButton("Start Game");
        startButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        startButton.setFont(new Font("Arial", Font.BOLD, 24));
        startButton.setBackground(Color.BLUE);
        startButton.setForeground(Color.WHITE);
        startButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3, false));
        startButton.setFocusPainted(false);
        startButton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        startButton.setMaximumSize(new Dimension(200, 50));

        startButton.addActionListener(_ -> {
            new SetUpPanel();
            dispose();
        });
        buttonPanel.add(startButton);
        dayPanel.add(buttonPanel, BorderLayout.WEST);
        dayPanel.add(label, BorderLayout.PAGE_START);
        dayPanel.add(buttonPanel, BorderLayout.CENTER);
    }

    private void setupNightPanel(JPanel nightPanel, CardLayout cardLayout, JPanel backgroundPanel) {
        nightPanel.setLayout(new BorderLayout());

        JLabel label = new JLabel("BATTLESHIP SIMULATOR - Night");
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 32));
        label.setForeground(Color.WHITE);

        JButton switchToDayButton = new JButton("Switch to Day");
        switchToDayButton.addActionListener(e -> cardLayout.show(backgroundPanel, "Day"));

        JPanel buttonPanel = new JPanel();
        buttonPanel.setOpaque(false);
        buttonPanel.add(switchToDayButton);

        JButton startButton = new JButton("Start Game");
        startButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        startButton.setFont(new Font("Arial", Font.BOLD, 24));
        startButton.setBackground(Color.BLUE);
        startButton.setForeground(Color.WHITE);
        startButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3, false));
        startButton.setFocusPainted(false);
        startButton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        startButton.setMaximumSize(new Dimension(200, 50));

        startButton.addActionListener(_ -> {
            new SetUpPanel();
            dispose();
        });
        buttonPanel.add(startButton);
        buttonPanel.add(Box.createVerticalGlue());
        nightPanel.add(buttonPanel, BorderLayout.WEST);
        nightPanel.add(label, BorderLayout.PAGE_START);
        nightPanel.add(buttonPanel, BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        new GameMenu();
    }
}
