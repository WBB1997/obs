import javax.swing.*;
import java.awt.*;

public class Start {
    public static void main(String[] args) {
        MainFrame mainFrame = new MainFrame();
        mainFrame.setSize(1000, 500);
        mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setResizable(false);
    }
}
