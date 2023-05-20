package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;

import Model.Researcher;

public class MainView extends JFrame {  // Main View class is just a UI class that will show up after Login. It has nothing to do like being Mediator or smth.
    private JLabel nameLabel;  // name of logged in researcher.
    private JTextArea followingResearchersTextArea;
    private JTextArea followerResearchersTextArea;
    private final JButton followButton; // When clicked, it should ask for username of desired researcher to follow.
    private final JButton readingListButton; // When clicked, Reading List View should be shown up.
    private final JButton unfollowButton; // When clicked, it should ask for username of desired researcher to unfollow.
    private final JButton readingListsOfResearchersButton;  // When clicked, User should see other Researchers' reading lists.

    public MainView() {
        unfollowButton = new JButton("Unfollow");
        followButton = new JButton("Follow");
        readingListButton = new JButton("My Reading List");
        readingListsOfResearchersButton = new JButton("Other Researchers' reading lists");
        initialize();
    }

    private void initialize() {
        setTitle("Researcher Page");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 600);
        setLocationRelativeTo(null); // center the JFrame on the screen

        JPanel panel = new JPanel(new GridLayout(5, 2));

        nameLabel = new JLabel();
        panel.add(nameLabel);

        JLabel bos = new JLabel();
        panel.add(bos);  // It is put in order to make UI much clearer


        JLabel followingResearchersLabel = new JLabel("Following Researchers:");
        panel.add(followingResearchersLabel);

        followingResearchersTextArea = new JTextArea();
        followingResearchersTextArea.setEditable(false);
        JScrollPane followingResearchersScrollPane = new JScrollPane(followingResearchersTextArea);
        panel.add(followingResearchersScrollPane);

        JLabel followerResearchersLabel = new JLabel("Follower Researchers:");
        panel.add(followerResearchersLabel);

        followerResearchersTextArea = new JTextArea();
        followerResearchersTextArea.setEditable(false);
        JScrollPane followerResearchersScrollPane = new JScrollPane(followerResearchersTextArea);
        panel.add(followerResearchersScrollPane);

        panel.add(followButton);
        panel.add(unfollowButton);
        panel.add(readingListButton);
        panel.add(readingListsOfResearchersButton);

        setContentPane(panel);
    }

    public void displayProfile(Researcher researcher) {
        nameLabel.setText("Researcher: " + researcher.getUsername());

        setTextAreaContent(followingResearchersTextArea, researcher.getFollowing());
        setTextAreaContent(followerResearchersTextArea, researcher.getFollower());
    }

    private void setTextAreaContent(JTextArea textArea, List<String> content) {
        StringBuilder sb = new StringBuilder();
        for (String item : content) {
            sb.append("- ").append(item).append("\n");
        }
        textArea.setText(sb.toString());
    }

    public void followListener(ActionListener listener) {
        followButton.addActionListener(listener);
    }

    public void unfollowListener(ActionListener listener) {
        unfollowButton.addActionListener(listener);
    }

    public void readingListListener(ActionListener listener) {
        readingListButton.addActionListener(listener);
    }

    public void readingListsOfResearchersListener(ActionListener listener) {
        readingListsOfResearchersButton.addActionListener(listener);
    }

}
