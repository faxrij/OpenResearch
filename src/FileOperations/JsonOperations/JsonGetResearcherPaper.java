package FileOperations.JsonOperations;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

public class JsonGetResearcherPaper {
    private static final String JSON_FILE_PATH = "reading-list.json";

    public Map<JSONArray, Integer> getNameOfPapers(String researcherUsername, String readingListName) {
        try {
            // Read the JSON file into a JSON array
            org.json.JSONArray jsonArray = readJsonFile();

            // Iterate through each object in the array
            Map<JSONArray, Integer> papersMap = iterateAndGetPapersMap(researcherUsername, readingListName, jsonArray);
            if (papersMap != null) return papersMap;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Map<JSONArray, Integer> iterateAndGetPapersMap(String researcherUsername, String readingListName, JSONArray jsonArray) throws JSONException {
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);

            // Check if the reading list matches the provided criteria
            Map<JSONArray, Integer> papersMap = ifEquals(researcherUsername, readingListName, i, jsonObject);
            if (papersMap != null) return papersMap;
        }
        return null;
    }

    private Map<JSONArray, Integer> ifEquals(String researcherUsername, String readingListName, int i, JSONObject jsonObject) throws JSONException {
        if (jsonObject.getString("readinglist_name").equals(readingListName) && jsonObject.getString("creator_researcher_name").equals(researcherUsername)) {

            // Get the array of paper names
            JSONArray nameOfPapers = jsonObject.getJSONArray("name_of_papers");

            // Create a map with the paper names and their indices
            Map<JSONArray, Integer> papersMap = new HashMap<>();
            papersMap.put(nameOfPapers, i);

            return papersMap;
        }
        return null;
    }

    private org.json.JSONArray readJsonFile() {
        try {
            FileInputStream fileInputStream = new FileInputStream(JSON_FILE_PATH);
            JSONTokener tokener = new JSONTokener(fileInputStream);
            return new org.json.JSONArray(tokener);
        } catch (FileNotFoundException | JSONException e) {
            e.printStackTrace();
        }
        return new org.json.JSONArray();
    }
}
