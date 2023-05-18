package Controller;

import Component.Researcher;
import Repository.ResearcherRepository;
import View.AddResearcherView;
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

    }

    public void displayProfilePage() {
        profileView.displayProfile(researchModel);
    }

    private class FollowListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            AddResearcherView addResearcherView = new AddResearcherView();
            addResearcherView.setVisible(true);

            AddResearcherController addResearcherController = new AddResearcherController(addResearcherView,
                    researcherRepository, researchModel, profileView);

        }
    }
}
