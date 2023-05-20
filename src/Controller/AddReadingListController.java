package Controller;

import Model.Researcher;
import FileOperations.JsonOperations.JsonAddReadingList;
import FileOperations.JsonOperations.JsonGetResearcherReadingLists;
import View.AddReadingListView;
import View.ReadingListView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class AddReadingListController {
    private final AddReadingListView addReadingListView;
    private final Researcher researcher;

    public AddReadingListController(AddReadingListView addReadingListView, Researcher researcher) {
        this.addReadingListView = addReadingListView;
        this.researcher = researcher;
        this.addReadingListView.submitListener(new AddReadingListListener());
    }

    private class AddReadingListListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String inputtedText = addReadingListView.getReadingListName();
            JsonGetResearcherReadingLists jsonGetResearcherReadingLists = new JsonGetResearcherReadingLists();

            JsonAddReadingList jsonAddReadingList = new JsonAddReadingList();
            jsonAddReadingList.addReadingList(researcher.getUsername(), inputtedText);

            addReadingListView.setVisible(false);

            List<String> readingLists = jsonGetResearcherReadingLists.getReadingListNamesForResearcher(researcher.getUsername());
            ReadingListView readingListView = new ReadingListView();
            readingListView.displayReadingLists(readingLists);
            readingListView.setVisible(true);

            ReadingListController readingListController = new ReadingListController(readingListView, researcher);

        }
    }
}
