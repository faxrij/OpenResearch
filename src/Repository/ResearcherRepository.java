package Repository;

import Component.Researcher;

import java.util.List;

public interface ResearcherRepository extends IFollowable, IUnfollowable, IAuthenticateable { //We put Interface here, because maybe in future we'll have researchers in db format, or json, or csv
    List<Researcher> getResearchers();
    boolean containsResearcher(String username);
}
