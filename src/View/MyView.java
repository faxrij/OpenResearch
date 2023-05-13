package View;

import javax.swing.*;
import java.awt.*;

public class MyView extends JFrame {
    private final JLabel label;
    private final JButton button;

    public MyView() {
        // Set the size and location of the JFrame
        setSize(500, 500);
        setLocationRelativeTo(null);

        // Create the components
        label = new JLabel();
        button = new JButton("Click Me!");

        // Create a container to hold the components
        Container container = getContentPane();
        container.setLayout(new FlowLayout());

        // Add the components to the container
        container.add(label);
        container.add(button);

        // Set the default close operation for the JFrame
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void setMessage(String message) {
        label.setText(message);
    }

    public JButton getButton() {
        return button;
    }
}
