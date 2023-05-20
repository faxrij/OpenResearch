package FileOperations.JsonOperations;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class JsonAddReadingList {

    private static final String JSON_FILE_PATH = "reading-list.json";

    public void addReadingList(String researcher, String readingListName) {
        JSONParser parser = new JSONParser();

        try (FileReader reader = new FileReader(JSON_FILE_PATH)) {
            JSONArray jsonArray = (JSONArray) parser.parse(reader);

            Random rand = new Random();
            String readingListId = String.valueOf(rand.nextInt(1000));

            JSONObject newReadingList = new JSONObject();
            addingNewReadingToJson(researcher, readingListName, jsonArray, readingListId, newReadingList);

            updateJson(jsonArray);

            System.out.println("Reading list added successfully.");
        } catch (ParseException | IOException e) {
            e.printStackTrace();
        }
    }

    private static void addingNewReadingToJson(String researcher, String readingListName, JSONArray jsonArray, String readingListId, JSONObject newReadingList) {
        newReadingList.put("number_of_papers", 0);
        newReadingList.put("readinglist_id", readingListId);
        newReadingList.put("readinglist_name", readingListName);
        newReadingList.put("name_of_papers", new JSONArray());
        newReadingList.put("creator_researcher_name", researcher);

        jsonArray.add(newReadingList);
    }

    private void updateJson(JSONArray jsonArray) throws IOException {
        try (FileWriter writer = new FileWriter(JSON_FILE_PATH)) {
            writer.write(jsonArray.toString());
        }
    }

}
