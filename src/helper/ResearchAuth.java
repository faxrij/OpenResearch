package helper;

import Component.Researcher;

import java.util.List;

public class ResearchAuth {
    public Researcher authenticate(String username, String password) {
        ResearcherHelper researcherHelper = new ResearcherHelper();
        List<Researcher> researchers = researcherHelper.getResearchersFrom("researchers.xml");
        for (Researcher researcher : researchers) {
            if (researcher.getUsername().equals(username) && researcher.getPassword().equals(password)) {
                return researcher;
            }
        }
        return null; // Authentication failed
    }
}
