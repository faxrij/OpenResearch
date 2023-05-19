package Controller;

import Component.Researcher;
import Reader.JsonReader;
import Repository.ResearcherRepository;
import View.ReadingListView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ReadingListController {
    private final ReadingListView readingListView;
    private final Researcher researcher;

    public ReadingListController(ReadingListView readingListView, Researcher researcher) {
        this.readingListView = readingListView;
//        this.researcherRepository = researcherRepository;
        this.researcher = researcher;
//        this.readingListView.addReadingListListener(new AddReadingListListener());
        displayReadingLists();
    }

    public void displayReadingLists() {
        JsonReader jsonReader = new JsonReader();
        List<String> readingLists = jsonReader.getReadingListNamesForResearcher(researcher.getUsername());
        readingListView.displayReadingLists(readingLists);

//        List<String> papers = jsonReader.getPapersForResearcher(researcher.getUsername());
//        readingListView.displayPapers(papers);
    }

    private class AddReadingListListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

        }

    }
}
