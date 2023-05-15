package Controller;

import Component.Researcher;
import View.ResearcherView;

public class ProfileController {
        private Researcher researchModel;
        private ResearcherView profileView;

        public ProfileController(Researcher researchModel, ResearcherView profileView) {
            this.researchModel = researchModel;
            this.profileView = profileView;
        }

        public void displayProfilePage() {
            profileView.displayProfile(researchModel);
        }
}
