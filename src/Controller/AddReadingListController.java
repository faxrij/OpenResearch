package Controller;

import Component.Researcher;
import Reader.JsonReader;
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
            JsonReader jsonReader = new JsonReader();
            jsonReader.addReadingList(researcher.getUsername(), inputtedText);
            addReadingListView.setVisible(false);

            List<String> readingLists = jsonReader.getReadingListNamesForResearcher(researcher.getUsername());
            ReadingListView readingListView = new ReadingListView();
            readingListView.displayReadingLists(readingLists);
            readingListView.setVisible(true);

            ReadingListController readingListController = new ReadingListController(readingListView, researcher);

        }
    }
}
