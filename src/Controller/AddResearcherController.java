package Controller;

import Component.Researcher;
import Repository.ResearcherRepository;
import View.AddResearcherView;
import View.ResearcherView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddResearcherController {
    private final ResearcherRepository researcherRepository;
    private final AddResearcherView addResearcherView;
    private final Researcher currentResearcher;
    private final ResearcherView profileView;

    public AddResearcherController(AddResearcherView addResearcherView, ResearcherRepository researcherRepository,
                                   Researcher currentResearcher, ResearcherView profileView) {
        this.researcherRepository = researcherRepository;
        this.addResearcherView = addResearcherView;
        this.currentResearcher = currentResearcher;
        this.addResearcherView.addResearcheristener(new AddResearcherListener());
        this.profileView = profileView;
    }

    private class AddResearcherListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

            String wantedResearcher = addResearcherView.getUsername();
            boolean exists = researcherRepository.containsResearcher(wantedResearcher);

            if (exists) {
                boolean isFollowed = researcherRepository.checkIfFollowed(currentResearcher.getUsername(), wantedResearcher);
                if (isFollowed) {
                    JOptionPane.showMessageDialog(addResearcherView, "Already following");
                }
                else {
                    ifNotFollowed(wantedResearcher);
                }
            }
            else {
                JOptionPane.showMessageDialog(addResearcherView, "Researcher with entered username does not exist");
            }
        }

        private void ifNotFollowed(String wantedResearcher) {
            currentResearcher.addFollowing(wantedResearcher);
            researcherRepository.addFollower(currentResearcher, wantedResearcher);
            addResearcherView.setVisible(false);
            profileView.displayProfile(currentResearcher);
        }
    }
}
