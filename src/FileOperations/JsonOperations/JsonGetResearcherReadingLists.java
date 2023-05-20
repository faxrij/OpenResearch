package FileOperations.JsonOperations;

import org.json.simple.*;
import org.json.simple.parser.JSONParser;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class JsonGetResearcherReadingLists { // Returns reading lists of provided researcher
    private static final String JSON_FILE_PATH = "reading-list.json";

    public List<String> getReadingListNamesForResearcher(String researcherName) {

        List<String> readingListNames = new ArrayList<>();

        try {
            JSONParser parser = new JSONParser();

            FileReader fileReader = new FileReader(JSON_FILE_PATH);
            JSONArray jsonArray = (JSONArray) parser.parse(fileReader);

            addReadingsToList(researcherName, readingListNames, jsonArray);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return readingListNames;
    }

    private void addReadingsToList(String researcherName, List<String> readingListNames, JSONArray jsonArray) {
        for (Object json : jsonArray) {
            JSONObject jsonObject = (JSONObject) json;
            String creatorName = (String) jsonObject.get("creator_researcher_name");
            String readingListName = (String) jsonObject.get("readinglist_name");

            if (creatorName.equals(researcherName)) {
                readingListNames.add(readingListName);
            }
        }
    }

}
