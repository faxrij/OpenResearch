package Controller;

import Component.Researcher;
import Repository.ResearcherRepository;
import View.ResearcherInputView;
import View.ResearcherView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UnfollowResearchController {
    private final ResearcherRepository researcherRepository;
    private final ResearcherInputView researcherInputView;
    private final Researcher currentResearcher;
    private final ResearcherView profileView;

    public UnfollowResearchController(ResearcherInputView researcherInputView, ResearcherRepository researcherRepository,
                                     Researcher currentResearcher, ResearcherView profileView) {
        this.researcherRepository = researcherRepository;
        this.researcherInputView = researcherInputView;
        this.currentResearcher = currentResearcher;
        this.researcherInputView.addActionListener(new UnfollowResearcherListener());
        this.profileView = profileView;
    }

    private class UnfollowResearcherListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

            String wantedResearcher = researcherInputView.getUsername();
            boolean exists = researcherRepository.containsResearcher(wantedResearcher);

            if (exists) {
                boolean isFollowed = researcherRepository.checkIfFollowed(currentResearcher.getUsername(), wantedResearcher);
                if (!isFollowed) {
                    JOptionPane.showMessageDialog(researcherInputView, "Not following");
                }
                else {
                    ifFollowed(wantedResearcher);
                }
            }
            else {
                JOptionPane.showMessageDialog(researcherInputView, "Researcher with entered username does not exist");
            }
        }

        private void ifFollowed(String wantedResearcher) {
            currentResearcher.removeFollowing(wantedResearcher);

            researcherRepository.unFollow(currentResearcher.getUsername(), wantedResearcher);

            researcherInputView.setVisible(false);
            profileView.displayProfile(currentResearcher);
        }
    }
}
