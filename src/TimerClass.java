import javax.swing.*;

public class TimerClass {
    private final JLabel timerLabel;
    private int elapsedSeconds;
    private final Timer timer;

    public TimerClass() {
        timerLabel = new JLabel("0", SwingConstants.CENTER);
        elapsedSeconds = 0;
        timer = new Timer(1000, e -> {
            elapsedSeconds++;
            timerLabel.setText(Integer.toString(elapsedSeconds));
        });
    }

    public JLabel getTimerLabel() {
        return timerLabel;
    }

    public void startTimer() {
        timer.start();
    }

    public void stopTimer() {
        timer.stop();
    }

    public int getElapsedTime() {
        return elapsedSeconds;
    }

    public void resetTimer() {
        stopTimer();
        elapsedSeconds = 0;
        timerLabel.setText("0");
    }
}
