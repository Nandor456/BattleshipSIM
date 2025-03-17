import javax.swing.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class TimeSaver {
    private File selectedFile;
    public TimeSaver() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Select a File to Save Timer");
        File initialDirectory = new File("C:/Java/Battleship");
        if (initialDirectory.exists()) {
            fileChooser.setCurrentDirectory(initialDirectory);
        }
        int result = fileChooser.showSaveDialog(null);

        if (result == JFileChooser.APPROVE_OPTION) {
            selectedFile = fileChooser.getSelectedFile();
        } else {
            selectedFile = new File("default_timer_log.txt");
        }
    }
    public void saveTimeToFile(int currentValue) {
        if (selectedFile != null) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(selectedFile, true))) {
                writer.write("Time elapsed: " + currentValue + " seconds");
                writer.newLine();
            } catch (IOException ex) {
                System.err.println("Error writing to file: " + ex.getMessage());
            }
        }
    }
}
