import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class WinnerPanel extends JFrame {
    Integer elapsedTime;
    public WinnerPanel(Integer elapsedTime) {
        this.elapsedTime = elapsedTime;
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setBounds(400, 100, 800, 600);

        JPanel backgroundPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                try {
                    ImageIcon backgroundImage = new ImageIcon(Objects.requireNonNull(getClass().getResource("/victory.png")));
                    g.drawImage(backgroundImage.getImage(), 0, 0, getWidth(), getHeight(), this);
                } catch (Exception e) {
                    System.err.println("Background image not found!");
                }
            }
        };

        backgroundPanel.setLayout(new BorderLayout());

        // "Victory!" label
        JLabel victory = new JLabel("Victory!");
        victory.setHorizontalAlignment(SwingConstants.CENTER);
        victory.setFont(new Font("Arial", Font.BOLD, 64));
        victory.setForeground(Color.WHITE);

        JLabel time = new JLabel("Finished in:" + elapsedTime.toString());
        time.setHorizontalAlignment(SwingConstants.CENTER);
        time.setFont(new Font("Arial", Font.BOLD, 64));
        time.setForeground(Color.WHITE);

        // Button panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        buttonPanel.setOpaque(false);
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(0, 50, 0, 0)); // Add a 50px left gap to the button panel

        JPanel buttonSaverPanel = new JPanel();
        buttonSaverPanel.setLayout(new FlowLayout());
        buttonSaverPanel.setOpaque(false);
        buttonSaverPanel.setBorder(BorderFactory.createEmptyBorder(200, 0, 0, 50));


        // "New Game" button
        JButton newGame = new JButton("New Game");
        newGame.setFont(new Font("Arial", Font.BOLD, 24));
        newGame.setBackground(Color.BLUE);
        newGame.setForeground(Color.WHITE);
        newGame.setFocusPainted(false);
        newGame.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3, false));
        newGame.setAlignmentX(Component.CENTER_ALIGNMENT);
        newGame.setMaximumSize(new Dimension(200, 50));

        // "Exit" button
        JButton exit = new JButton("EXIT");
        exit.setFont(new Font("Arial", Font.BOLD, 24));
        exit.setBackground(Color.BLUE);
        exit.setForeground(Color.WHITE);
        exit.setFocusPainted(false);
        exit.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        exit.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3, false));
        exit.setAlignmentX(Component.CENTER_ALIGNMENT);
        exit.setMaximumSize(new Dimension(200, 50));

        JButton timeSaver = new JButton("Save the time");
        timeSaver.setFont(new Font("Arial", Font.BOLD, 24));
        timeSaver.setBackground(Color.BLUE);
        timeSaver.setForeground(Color.WHITE);
        timeSaver.setFocusPainted(false);
        timeSaver.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        timeSaver.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3, false));
        timeSaver.setAlignmentX(Component.CENTER_ALIGNMENT);
        timeSaver.setMaximumSize(new Dimension(50, 50));


        // Add components to button panel
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 200)));
        buttonPanel.add(newGame);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 50)));
        buttonPanel.add(exit);
        buttonSaverPanel.add(timeSaver);

        // Add components to the background panel
        backgroundPanel.add(victory, BorderLayout.NORTH);
        backgroundPanel.add(buttonPanel, BorderLayout.WEST);
        backgroundPanel.add(time, BorderLayout.SOUTH);
        backgroundPanel.add(buttonSaverPanel, BorderLayout.EAST);

        exit.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SoundPlayer.stopSound();
                dispose();
            }
        });

        newGame.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                SoundPlayer.stopSound();
                new SetUpPanel();
            }
        });

        timeSaver.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TimeSaver saver = new TimeSaver();
                saver.saveTimeToFile(elapsedTime);
            }
        });

        add(backgroundPanel);
        SoundPlayer.playSound("Drunken Sailer - Irish Rovers.wav");
        setVisible(true);
    }
}
