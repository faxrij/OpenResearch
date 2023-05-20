package FileOperations.JsonOperations;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class JsonGetOtherResearchersReadingLists { // Returns reading lists of all researchers except provided one
    private static final String JSON_FILE_PATH = "reading-list.json";

    public JSONArray filterReadingLists(String currentResearcherName) {
        JSONArray filteredList = new JSONArray();

        try {
            // Read the JSON file
            String jsonString = new String(Files.readAllBytes(Paths.get(JSON_FILE_PATH)));
            JSONArray allReadingLists = new JSONArray(jsonString);

            for (int i = 0; i < allReadingLists.length(); i++) {
                JSONObject readingList = allReadingLists.getJSONObject(i);
                String creatorName = readingList.getString("creator_researcher_name");

                if (!creatorName.equals(currentResearcherName)) {
                    filteredList.put(readingList);
                }
            }
        } catch (JSONException | IOException e) {
            e.printStackTrace();
        }

        return filteredList;
    }
}
