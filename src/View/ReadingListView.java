package View;

import Reader.JsonReader;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;

public class ReadingListView extends JFrame {
    private JList<String> readingListsJList;
    private JList<String> papersJList;
    private final JButton addReadingListButton;
    private final JButton removePaperButton;
    private final JButton seePaperButton;

    public ReadingListView() {
        this.addReadingListButton = new JButton("Add Reading List");
        this.removePaperButton = new JButton("Remove Paper");
        this.seePaperButton = new JButton("See Papers");
        initialize();
    }

    public JList<String> getReadingListsJList() {
        return readingListsJList;
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

        DefaultListModel<String> papersListModel = new DefaultListModel<>();
        papersJList = new JList<>(papersListModel);
        JScrollPane papersScrollPane = new JScrollPane(papersJList);
        panel.add(papersScrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout());

        buttonPanel.add(addReadingListButton);
        buttonPanel.add(removePaperButton);
        buttonPanel.add(seePaperButton);

        panel.add(buttonPanel, BorderLayout.SOUTH);

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

    public JList<String> getPapersJList() {
        return papersJList;
    }

    private void displayPapersForReadingList(String readingListName) {
        List<String> papersList = getPapersForReadingList(readingListName);

        DefaultListModel<String> papersListModel = new DefaultListModel<>();
        for (String paper : papersList) {
            papersListModel.addElement(paper);
        }
        papersJList.setModel(papersListModel);
    }

    private List<String> getPapersForReadingList(String readingListName) {
        JsonReader jsonReader = new JsonReader();
        return jsonReader.getPapersOfReadingList(readingListName);
    }

    public void removePaperListener(ActionListener listener) {
        removePaperButton.addActionListener(listener);
    }

    public void addPaperListener(ActionListener listener) {
        seePaperButton.addActionListener(listener);
    }

    public void addReadingListListener(ActionListener listener) {
        addReadingListButton.addActionListener(listener);
    }

}
