package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class AddReadingListView extends JFrame {
    private final JTextField readingInput;
    private final JButton submit;

    public AddReadingListView() {
        // set up the JFrame properties
        setTitle("Add Reading List");
        setSize(600, 400);
        setLocationRelativeTo(null); // center the JFrame on the screen

        // set up the user interface elements
        JPanel panel = new JPanel(new GridLayout(2, 5));

        panel.add(new JLabel("Enter name for Reading List:"));
        readingInput = new JTextField();
        panel.add(readingInput);

        submit = new JButton("Submit");
        panel.add(submit);
        setContentPane(panel);

    }

    public String getReadingListName() {
        return readingInput.getText();
    }

    public void submitListener(ActionListener listener) {
        submit.addActionListener(listener);
    }
}
