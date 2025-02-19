import java.awt.*;
import javax.swing.*;

public class Chess extends JFrame {
    public Chess() {  // Fixed constructor
        setTitle("Chess");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(8, 8));

        boolean white = true;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                JPanel panel = new JPanel();
                panel.setBackground(white ? Color.WHITE : Color.BLACK);
                add(panel);
                white = !white;
            }
            white = !white;
        }

        setVisible(true);
    }
}
