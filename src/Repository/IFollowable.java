package Repository;

public interface IFollowable {
    boolean checkIfFollowed(String current, String wanted);

    void addFollower(String currentResearcher, String toBeFollowed);
}
