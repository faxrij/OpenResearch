package View;

import Reader.JsonReader;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class ReadingListView extends JFrame {
    private JList<String> readingListsJList;
    private JTextArea papersTextArea;
    private final JButton addReadingListButton;

    public ReadingListView() {
        this.addReadingListButton = new JButton("Add Reading List");
        initialize();
    }

    private void initialize() {
        setTitle("Reading List Page");
        setSize(500, 600);
        setLocationRelativeTo(null); // center the JFrame on the screen

        JPanel panel = new JPanel(new BorderLayout());

        DefaultListModel<String> listModel = new DefaultListModel<>();
        readingListsJList = new JList<>(listModel);
        JScrollPane readingListsScrollPane = new JScrollPane(readingListsJList);
        panel.add(readingListsScrollPane, BorderLayout.WEST);

        papersTextArea = new JTextArea();
        papersTextArea.setEditable(false);
        JScrollPane papersScrollPane = new JScrollPane(papersTextArea);
        panel.add(papersScrollPane, BorderLayout.CENTER);

        panel.add(addReadingListButton, BorderLayout.SOUTH);

        readingListsJList.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int selectedIndex = readingListsJList.getSelectedIndex();
                if (selectedIndex != -1) {
                    String selectedReadingList = readingListsJList.getSelectedValue();
                    displayPapersForReadingList(selectedReadingList);
                }
            }
        });

        setContentPane(panel);
    }

    public void displayReadingLists(List<String> stringList) {
        DefaultListModel<String> listModel = new DefaultListModel<>();
        for (String item : stringList) {
            listModel.addElement(item);
        }
        readingListsJList.setModel(listModel);
    }

    private void displayPapersForReadingList(String readingListName) {
        // Retrieve papers for the selected reading list based on readingListName
        // You can update this method according to your data model and retrieval logic
        List<String> papersList = getPapersForReadingList(readingListName);

        StringBuilder sb = new StringBuilder();
        for (String paper : papersList) {
            sb.append("- ").append(paper).append("\n");
        }
        papersTextArea.setText(sb.toString());
    }

    private List<String> getPapersForReadingList(String readingListName) {
        // Placeholder method to retrieve papers for the selected reading list
        // Replace this with your actual data retrieval logic
        // Return a list of papers for the given reading list name
        JsonReader jsonReader = new JsonReader();
        return jsonReader.getPapersOfReadingList(readingListName);
    }
}
