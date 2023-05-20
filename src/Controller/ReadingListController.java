package Controller;

import Component.Paper;
import Component.Researcher;
import Reader.CsvReader;
import Reader.JsonReader;
import View.AddReadingListView;
import View.PaperView;
import View.ReadingListView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ReadingListController {
    private final ReadingListView readingListView;
    private final Researcher researcher;

    public ReadingListController(ReadingListView readingListView, Researcher researcher) {
        this.readingListView = readingListView;
        this.researcher = researcher;
        this.readingListView.addPaperListener(new AddPaperListener());
        this.readingListView.removePaperListener(new RemovePaperListener());
        this.readingListView.addReadingListListener(new AddReadingListListener());
        displayReadingLists();
    }

    public void displayReadingLists() {
        JsonReader jsonReader = new JsonReader();
        List<String> readingLists = jsonReader.getReadingListNamesForResearcher(researcher.getUsername());
        readingListView.displayReadingLists(readingLists);
    }

    private class AddPaperListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            CsvReader csvReader = new CsvReader();
            List<Paper> papers = csvReader.parseCSV();
            PaperView paperView = new PaperView(papers);
            paperView.setVisible(true);
            PaperController paperController = new PaperController(paperView);
        }
    }


    private class AddReadingListListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            readingListView.setVisible(false); // Here we provide better UX -> user experience
            AddReadingListView addReadingListView = new AddReadingListView();
            addReadingListView.setVisible(true);
            AddReadingListController addReadingListController = new AddReadingListController(addReadingListView, researcher);
        }
    }

    private class RemovePaperListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String selectedPaper = readingListView.getPapersJList().getSelectedValue();

            // Get the selected reading list
            String selectedReadingList = readingListView.getReadingListsJList().getSelectedValue();

            JsonReader jsonReader = new JsonReader();

            // Remove the paper from the reading list
            jsonReader.removePaperFromReadingList(selectedReadingList, selectedPaper);
            DefaultListModel<String> papersListModel = (DefaultListModel<String>) readingListView.getPapersJList().getModel();
            papersListModel.removeElement(selectedPaper);

        }
    }

}