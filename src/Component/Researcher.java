package Component;

import java.util.List;

public class Researcher {
    private final String username;
    private final String password;
    private List<String> follower;

    private List<String> following;

    public Researcher(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setFollower(List<String> follower) {
        this.follower = follower;
    }

    public void setFollowing(List<String> following) {
        this.following = following;
    }

    public void addFollower(String researcher) {
        follower.add(researcher);
    }

    public void addFollowing(String researcher) {
        following.add(researcher);
    }

    public List<String> getFollower() {
        return follower;
    }

    public List<String> getFollowing() {
        return following;
    }
}
