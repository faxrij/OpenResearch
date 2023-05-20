package FileOperations.JsonOperations;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.*;

public class JsonWriter {
    private static final String JSON_FILE_PATH = "reading-list.json";

    public void addPaperToReadingList(JSONArray nameOfPapers, int i, String paperName) {
        try {
            JSONArray jsonArray = readJsonFile();
            JSONObject jsonObject = jsonArray.getJSONObject(i);

            // Append the new paper name to the array
            nameOfPapers.put(paperName);

            // Update the JSON object with the modified array
            jsonObject.put("name_of_papers", nameOfPapers);

            // Update the number of papers
            int numberOfPapers = nameOfPapers.length();
            jsonObject.put("number_of_papers", numberOfPapers);

            // Replace the object in the JSON array
            jsonArray.put(i, jsonObject);

            // Write the updated JSON array back to the file
            writeJsonFile(jsonArray);

            // Print a success message
            System.out.println("Paper added successfully.");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void writeJsonFile(JSONArray jsonArray) {
        try (FileWriter writer = new FileWriter(JSON_FILE_PATH)) {
            writer.write(jsonArray.toString());
            System.out.println("JSON file written successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private JSONArray readJsonFile() {
        try {
            FileInputStream fileInputStream = new FileInputStream(JSON_FILE_PATH);
            JSONTokener tokener = new JSONTokener(fileInputStream);
            return new JSONArray(tokener);
        } catch (FileNotFoundException | JSONException e) {
            e.printStackTrace();
        }
        return new JSONArray();
    }
}
