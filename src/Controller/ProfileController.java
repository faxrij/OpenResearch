package Controller;

import Component.Researcher;
import Reader.JsonReader;
import Repository.ResearcherRepository;
import View.ReadingListView;
import View.ResearcherInputView;
import View.ResearcherView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ProfileController {
    private final Researcher researchModel;
    private final ResearcherView profileView;
    private final ResearcherRepository researcherRepository;

    public ProfileController(Researcher researchModel, ResearcherView profileView, ResearcherRepository researcherRepository) {
        this.researchModel = researchModel;
        this.profileView = profileView;
        this.researcherRepository = researcherRepository;
        this.profileView.followListener(new FollowListener());
        this.profileView.unfollowListener(new UnfollowListener());
        this.profileView.readingListListener(new ReadingListListener());
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
}
