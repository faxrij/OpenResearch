package FileOperations.XmlOperations;

import Interface.ResearcherRepository;
import Model.Researcher;
import helper.FollowResearcher;
import helper.ResearchAuth;
import helper.ResearcherHelper;
import helper.UnfollowResearcher;

import java.util.List;

public class XmlResearcherOperations implements ResearcherRepository {
    private static final String XML_FILE_PATH = "researchers.xml";

    @Override
    public List<Researcher> getResearchers() {
        ResearcherHelper researcherHelper = new ResearcherHelper();
        return researcherHelper.getResearchersFrom(XML_FILE_PATH);
    }

    @Override
    public Researcher authenticate(String username, String password) {
        ResearchAuth researchAuth = new ResearchAuth();
        return researchAuth.authenticate(username, password);
    }

    @Override
    public boolean containsResearcher(String username) {
        List<Researcher> researchers = getResearchers();
        for (Researcher researcher : researchers) {
            if (researcher.getUsername().equals(username)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean checkIfFollowed(String current, String wanted) {
        List<Researcher> researchers = getResearchers();
        for (Researcher researcher : researchers) {
            if (researcher.getUsername().equals(current) && researcher.getFollowing().contains(wanted)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void addFollower(String currentResearcher, String toBeFollowed) {
        FollowResearcher followResearcher = new FollowResearcher();
        followResearcher.addFollower(currentResearcher, toBeFollowed);
    }

    @Override
    public void unFollow(String currentResearcher, String toBeUnfollowed) {
        UnfollowResearcher unfollowResearcher = new UnfollowResearcher();
        unfollowResearcher.unFollow(currentResearcher, toBeUnfollowed);
    }
}
