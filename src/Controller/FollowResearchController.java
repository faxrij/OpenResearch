package Controller;

import Component.Researcher;
import Repository.ResearcherRepository;
import View.ResearcherInputView;
import View.ResearcherView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FollowResearchController {
    private final ResearcherRepository researcherRepository;
    private final ResearcherInputView researcherInputView;
    private final Researcher currentResearcher;
    private final ResearcherView profileView;

    public FollowResearchController(ResearcherInputView researcherInputView, ResearcherRepository researcherRepository,
                                    Researcher currentResearcher, ResearcherView profileView) {
        this.researcherRepository = researcherRepository;
        this.researcherInputView = researcherInputView;
        this.currentResearcher = currentResearcher;
        this.researcherInputView.addActionListener(new FollowResearcherListener());
        this.profileView = profileView;
    }

    private class FollowResearcherListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

            String wantedResearcher = researcherInputView.getUsername();
            boolean exists = researcherRepository.containsResearcher(wantedResearcher);

            if (exists) {
                boolean isFollowed = researcherRepository.checkIfFollowed(currentResearcher.getUsername(), wantedResearcher);
                if (isFollowed) {
                    JOptionPane.showMessageDialog(researcherInputView, "Already following");
                } else {
                    ifNotFollowed(wantedResearcher);
                }
            } else {
                JOptionPane.showMessageDialog(researcherInputView, "Researcher with entered username does not exist");
            }
        }

        private void ifNotFollowed(String wantedResearcher) {
            currentResearcher.addFollowing(wantedResearcher);

            researcherRepository.addFollower(currentResearcher.getUsername(), wantedResearcher);

            researcherInputView.setVisible(false);
            profileView.displayProfile(currentResearcher);
        }
    }
}
