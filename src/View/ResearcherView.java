package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;

import Component.Researcher;

public class ResearcherView extends JFrame {
    private JLabel nameLabel;
    private JTextArea followingResearchersTextArea;
    private JTextArea followerResearchersTextArea;
    private final JButton followButton;
    private final JButton readingListButton;
    private final JButton unfollowButton;

    public ResearcherView() {
        unfollowButton = new JButton("Unfollow");
        followButton = new JButton("Follow");
        readingListButton = new JButton("See Reading List");
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
}
