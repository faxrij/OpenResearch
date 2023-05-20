package Controller;

import FileOperations.JsonOperations.JsonGetOtherResearchersReadingLists;
import Model.Researcher;
import Interface.ResearcherRepository;
import View.ReadingListView;
import View.ResearcherInputView;
import View.MainView;
import View.ResearchersReadingListView;
import org.json.JSONArray;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainController {
    // Main Controller is just a Controller class for MainView class. It has nothing to do with being Mediator or having multiple Controllers etc.
    private final Researcher researchModel;
    private final MainView profileView;
    private final ResearcherRepository researcherRepository;

    public MainController(Researcher researchModel, MainView profileView, ResearcherRepository researcherRepository) {
        this.researchModel = researchModel;
        this.profileView = profileView;
        this.researcherRepository = researcherRepository;
        this.profileView.followListener(new FollowListener());
        this.profileView.unfollowListener(new UnfollowListener());
        this.profileView.readingListListener(new ReadingListListener());
        this.profileView.readingListsOfResearchersListener(new ReadingListOtherResearcherListener());
    }

    public void displayProfilePage() {
        profileView.displayProfile(researchModel);
    }

    private class FollowListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            ResearcherInputView researcherInputView = new ResearcherInputView();
            researcherInputView.setVisible(true);

            FollowResearchController researcherInputController = new FollowResearchController(researcherInputView,
                    researcherRepository, researchModel, profileView);

        }
    }

    private class UnfollowListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            ResearcherInputView researcherInputView = new ResearcherInputView();
            researcherInputView.setVisible(true);

            UnfollowResearchController researcherInputController = new UnfollowResearchController(researcherInputView,
                    researcherRepository, researchModel, profileView);

        }
    }

    private class ReadingListListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            ReadingListView readingListView = new ReadingListView();
            readingListView.setVisible(true);

            ReadingListController readingListController = new ReadingListController(readingListView,
                    researchModel);

        }
    }

    private class ReadingListOtherResearcherListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

            JsonGetOtherResearchersReadingLists reader = new JsonGetOtherResearchersReadingLists();
            JSONArray array = reader.filterReadingLists(researchModel.getUsername());
            ResearchersReadingListView readingListView = new ResearchersReadingListView(array);
            readingListView.setVisible(true);

        }
    }
}
