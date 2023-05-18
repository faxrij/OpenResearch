package Repository;

import Component.Researcher;

import java.util.List;

public interface ResearcherRepository { //WE put Interface here, because maybe in future we'll have researchers in db format, or json, or csv
    List<Researcher> getResearchers();
    Researcher authenticate(String username, String password); //IAuthable
    boolean containsResearcher(String username); //
    boolean checkIfFollowed(String current, String wanted); //IFollowable
    void addFollower(String currentResearcher, String toBeFollowed); // IFollowable
}
