package View;

import Model.ArticlePaper;
import Model.ConferencePaper;
import Model.Paper;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;

public class PaperView extends JFrame {
    private JTextArea textArea;
    private JButton downloadButton;
    private JButton addToReadingListButton;
    private JList<Paper> paperList;
    private JComboBox<String> dropdownMenu;
    private final JList<String> readingListsJList;

    public PaperView(List<Paper> papers, JList<String> readingListsJList) {
        this.readingListsJList = readingListsJList;
        initializeUI();
        populatePaperList(papers);
    }

    private void initializeUI() {
        setTitle("Papers View");
        setSize(1000, 700);
        setLocationRelativeTo(null);

        textArea = new JTextArea();
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);
        getContentPane().add(scrollPane, BorderLayout.CENTER);

        downloadButton = new JButton("Download");
        addToReadingListButton = new JButton("Add To Reading List");

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(downloadButton);
        buttonPanel.add(addToReadingListButton);

        getContentPane().add(buttonPanel, BorderLayout.SOUTH);

        paperList = new JList<>();
        paperList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        paperList.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                Paper selectedPaper = paperList.getSelectedValue();
                if (selectedPaper != null) {
                    displayPaperDetails(selectedPaper);
                }
            }
        });
        JScrollPane listScrollPane = new JScrollPane(paperList);
        listScrollPane.setPreferredSize(new Dimension(300, getHeight()));
        getContentPane().add(listScrollPane, BorderLayout.WEST);

        // Dropdown menu
        JLabel dropdownLabel = new JLabel("Choose a Reading List to add the Paper:");
        dropdownMenu = new JComboBox<>();
        ListModel<String> readingListModel = readingListsJList.getModel();

        int numberOfElements = readingListModel.getSize();

        for (int i = 0; i < numberOfElements; i++) {
            dropdownMenu.addItem(readingListModel.getElementAt(i));
        }

        JPanel dropdownPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        dropdownPanel.add(dropdownLabel);
        dropdownPanel.add(dropdownMenu);
        getContentPane().add(dropdownPanel, BorderLayout.NORTH);

        setVisible(true);
    }


    public void downloadButtonListener(ActionListener listener) {
        downloadButton.addActionListener(listener);
    }

    public void addToReadingListButtonListener(ActionListener listener) {
        addToReadingListButton.addActionListener(listener);
    }

    private void populatePaperList(List<Paper> papers) {
        DefaultListModel<Paper> listModel = new DefaultListModel<>();
        for (Paper paper : papers) {
            listModel.addElement(paper);
        }
        paperList.setModel(listModel);

        if (!papers.isEmpty()) {
            displayPaperDetails(papers.get(0));
        }
    }

    private void displayPaperDetails(Paper paper) {
        if (SwingUtilities.isEventDispatchThread()) {
            updatePaperDetails(paper);
        } else {
            SwingUtilities.invokeLater(() -> updatePaperDetails(paper));
        }
    }

    private void updatePaperDetails(Paper paper) {
        StringBuilder sb = new StringBuilder();
        if (paper.getClass().equals(ArticlePaper.class)) {
            ArticlePaper articlePaper = (ArticlePaper) paper;

            sb.append("Volume: ").append(articlePaper.getVolume()).append("\n");
            sb.append("Number: ").append(articlePaper.getNumber()).append("\n");
            sb.append("Journal: ").append(articlePaper.getJournal()).append("\n");
        } else {
            ConferencePaper conferencePaper = (ConferencePaper) paper;
            sb.append("BookTitle: ").append((conferencePaper).getBookTitle()).append("\n");
        }
        // Append the common paper details
        sb.append("Authors: ").append(paper.getAuthors()).append("\n");
        sb.append("Title: ").append(paper.getTitle()).append("\n");
        sb.append("Year: ").append(paper.getYear()).append("\n");
        // sb.append("Issue: ").append(paper.getDOI()).append("\n");
        sb.append("DOI: ").append(paper.getDOI()).append("\n");
        textArea.setText(sb.toString());
    }

    public JList<Paper> getPaperList() {
        return paperList;
    }

    public JComboBox<String> getDropdownMenu() {
        return dropdownMenu;
    }
}
