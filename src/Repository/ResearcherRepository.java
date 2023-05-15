package Repository;

import Component.Researcher;

import java.util.List;

public interface ResearcherRepository { //WE put Interface here, because maybe in future we'll have researchers in db format, or json, or csv
    List<Researcher> getResearchers();
    Researcher authenticate(String username, String password);
}
