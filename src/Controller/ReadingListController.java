package Controller;

import FileOperations.JsonOperations.JsonRemove;
import Model.Paper;
import Model.Researcher;
import FileOperations.CsvOperations.CsvReader;
import FileOperations.JsonOperations.JsonGetResearcherReadingLists;
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
        JsonGetResearcherReadingLists jsonGetResearcherReadingLists = new JsonGetResearcherReadingLists();
        List<String> readingLists = jsonGetResearcherReadingLists.getReadingListNamesForResearcher(researcher.getUsername());
        readingListView.displayReadingLists(readingLists);
    }

    private class AddPaperListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            CsvReader csvReader = new CsvReader();
            List<Paper> papers = csvReader.parseCSV();
            PaperView paperView = new PaperView(papers, readingListView.getReadingListsJList());
            readingListView.setVisible(false);
            PaperController paperController = new PaperController(paperView, researcher);
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

            JsonRemove jsonRemove = new JsonRemove();

            // Remove the paper from the reading list
            jsonRemove.removePaperFromReadingList(selectedReadingList, selectedPaper);
            DefaultListModel<String> papersListModel = (DefaultListModel<String>) readingListView.getPapersJList().getModel();
            papersListModel.removeElement(selectedPaper);
        }
    }
}
