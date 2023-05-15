package View;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import Component.Researcher;


public class ResearcherView {
    private JFrame frame;
    private JLabel nameLabel;
    private JTextArea readingListsTextArea;
    private JTextArea followingResearchersTextArea;
    private JTextArea followerResearchersTextArea;

    public ResearcherView() {
        initialize();
    }

    private void initialize() {
        frame = new JFrame();
        frame.setTitle("Profile Page");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(4, 1));

        nameLabel = new JLabel();
        panel.add(nameLabel);

        JLabel readingListsLabel = new JLabel("Reading Lists:");
        panel.add(readingListsLabel);

        readingListsTextArea = new JTextArea();
        readingListsTextArea.setEditable(false);
        JScrollPane readingListsScrollPane = new JScrollPane(readingListsTextArea);
        panel.add(readingListsScrollPane);

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

        frame.add(panel);
    }

    public void displayProfile(Researcher researcher) {
        nameLabel.setText("Researcher: " + researcher.getUsername());
//        setTextAreaContent(readingListsTextArea, researcher.getReadingLists());
        setTextAreaContent(followingResearchersTextArea, researcher.getFollowing());
        setTextAreaContent(followerResearchersTextArea, researcher.getFollower());

        frame.setVisible(true);
    }

    private void setTextAreaContent(JTextArea textArea, List<String> content) {
        StringBuilder sb = new StringBuilder();
        for (String item : content) {
            sb.append("- ").append(item).append("\n");
        }
        textArea.setText(sb.toString());
    }
}
