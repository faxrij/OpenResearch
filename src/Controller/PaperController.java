package Controller;

import FileOperations.CsvOperations.CsvUpdate;
import FileOperations.JsonOperations.JsonGetResearcherPaper;
import Model.Paper;
import Model.Researcher;
import FileOperations.JsonOperations.JsonChecker;
import FileOperations.JsonOperations.JsonWriter;
import View.PaperView;
import org.json.JSONArray;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collection;
import java.util.Map;

public class PaperController { // Paper View Controller
    private final PaperView paperView;
    private final Researcher researcher;


    public PaperController(PaperView paperView, Researcher researcher) {

        this.paperView = paperView;
        this.researcher = researcher;
        this.paperView.downloadButtonListener(new DownloadButtonListener());
        this.paperView.addToReadingListButtonListener(new AddToReadingListButtonListener());
    }

    private class DownloadButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            Paper selectedPaper = paperView.getPaperList().getSelectedValue();
            if (selectedPaper != null) {

                CsvUpdate csvUpdate = new CsvUpdate();

                csvUpdate.updateCSV(selectedPaper.getTitle());

                JOptionPane.showMessageDialog(paperView,
                        "Successfully Downloaded.");

            } else {
                JOptionPane.showMessageDialog(paperView,
                        "No paper selected. Please select a paper to download.");
            }
        }
    }

    private class AddToReadingListButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String selectedReadingList = (String) paperView.getDropdownMenu().getSelectedItem();
            Paper selectedPaper = paperView.getPaperList().getSelectedValue();

            JsonWriter jsonWriter = new JsonWriter();
            JsonGetResearcherPaper jsonGetResearcherPaper = new JsonGetResearcherPaper();

            Map<JSONArray, Integer> nameOfPapersAndJsonObject = jsonGetResearcherPaper.getNameOfPapers(researcher.getUsername(), selectedReadingList);

            JsonChecker jsonChecker = new JsonChecker();

            Collection<Integer> values = nameOfPapersAndJsonObject.values();
            Collection<JSONArray> keySet = nameOfPapersAndJsonObject.keySet();


            if (!values.isEmpty()) {
                if (selectedPaper != null) {

                    boolean exists = jsonChecker.exists(keySet.iterator().next(), selectedPaper.getTitle());

                    if (!exists) {
                        jsonWriter.addPaperToReadingList(keySet.iterator().next(), values.iterator().next(), selectedPaper.getTitle());
                        JOptionPane.showMessageDialog(paperView, "Successfully Added.");
                    } else {
                        JOptionPane.showMessageDialog(paperView, "Paper already exists in the Reading List.");
                    }
                } else {
                    JOptionPane.showMessageDialog(paperView, "Please select a Paper");
                }
            }
        }
    }
}
