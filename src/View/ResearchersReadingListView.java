package View;

import javax.swing.*;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ResearchersReadingListView extends JFrame { // Other researchers reading lists.

    private final JTextArea textArea;

    public ResearchersReadingListView(JSONArray readingLists) {
        setTitle("Researchers Reading List View");
        setSize(500, 500);
        setLocationRelativeTo(null);

        textArea = new JTextArea();
        textArea.setEditable(false);

        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        // Add the JScrollPane to the content pane
        getContentPane().add(scrollPane);

        // Display the reading lists

        displayReadingLists(readingLists);

        setVisible(true);
    }

    private void displayReadingLists(JSONArray readingLists) {
        // Create a StringBuilder to store the formatted text
        StringBuilder sb = new StringBuilder();

        try {

            // Iterate over the reading lists and append the relevant information
            for (int i = 0; i < readingLists.length(); i++) {
                JSONObject readingList = readingLists.getJSONObject(i);

                String readingListName = readingList.getString("readinglist_name");
                JSONArray nameOfPapers = readingList.getJSONArray("name_of_papers");
                String creatorName = readingList.getString("creator_researcher_name");

                sb.append("Reading List Name: ").append(readingListName).append("\n");
                sb.append("Creator Researcher Name: ").append(creatorName).append("\n");
                sb.append("Papers: ").append(nameOfPapers.toString()).append("\n\n");
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        // Set the formatted text in the JTextArea
        textArea.setText(sb.toString());
    }
}
