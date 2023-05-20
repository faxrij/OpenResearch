package Controller;

import Component.Paper;
import View.PaperView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PaperController {
    private final PaperView paperView;

    public PaperController(PaperView paperView) {

        this.paperView = paperView;
        this.paperView.downloadButtonListener(new DownloadButtonListener());
    }

    private class DownloadButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            Paper selectedPaper = paperView.getPaperList().getSelectedValue();
            if (selectedPaper != null) {
                // Perform the download action with the selected paper
                // ...
            } else {
                JOptionPane.showMessageDialog(paperView,
                         "No paper selected. Please select a paper to download.");
            }
        }
    }

    private class AddPaperButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Handle the "Add Paper" button action
            // ...
        }
    }
}
