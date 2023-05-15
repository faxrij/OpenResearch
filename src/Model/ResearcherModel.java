package Model;

import Component.Researcher;
import Repository.ResearcherRepository;

import java.util.List;

public class ResearcherModel {
    private final ResearcherRepository researcherRepository;

    public ResearcherModel(ResearcherRepository researcherRepository) {
        this.researcherRepository = researcherRepository;
    }

    public boolean authenticate(String username, String password) {
        List<Researcher> researchers = researcherRepository.getResearchers();
        for (Researcher researcher : researchers) {
            if (researcher.getUsername().equals(username) && researcher.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }
}
