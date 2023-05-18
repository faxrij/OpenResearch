package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class AddResearcherView extends JFrame {
    private final JTextField usernameField;
    private final JButton submit;
    public AddResearcherView() {
        // set up the JFrame properties
        setTitle("AddResearcher");
        setSize(600, 400);
        setLocationRelativeTo(null); // center the JFrame on the screen

        // set up the user interface elements
        JPanel panel = new JPanel(new GridLayout(2, 5));

        panel.add(new JLabel("Add the Username of Researcher:"));
        usernameField = new JTextField();
        panel.add(usernameField);

        submit = new JButton("Submit");
        panel.add(submit);
        setContentPane(panel);

    }

    public String getUsername() {
        return usernameField.getText();
    }

    public void addResearcherListener(ActionListener listener) {
        submit.addActionListener(listener);
    }

}
