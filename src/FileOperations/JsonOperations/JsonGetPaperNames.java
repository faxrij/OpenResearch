package FileOperations.JsonOperations;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class JsonGetPaperNames {

    private static final String JSON_FILE_PATH = "reading-list.json";

    public List<String> getPapersOfReadingList(String readingListName) {
        List<String> papers = new ArrayList<>();
        try {
            JSONParser parser = new JSONParser();
            FileReader fileReader = new FileReader(JSON_FILE_PATH);

            JSONArray jsonArray = (JSONArray) parser.parse(fileReader);

            iterateAndGetPaperNames(readingListName, papers, jsonArray);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return papers;
    }

    private void iterateAndGetPaperNames(String readingListName, List<String> papers, JSONArray jsonArray) {
        for (Object json : jsonArray) {
            JSONObject jsonObject = (JSONObject) json;
            String listName = (String) jsonObject.get("readinglist_name");

            if (ifReadingNamesAreSame(readingListName, papers, jsonObject, listName)) break;
        }
    }

    private boolean ifReadingNamesAreSame(String readingListName, List<String> papers, JSONObject jsonObject, String listName) {
        if (listName.equals(readingListName)) {
            JSONArray papersArray = (JSONArray) jsonObject.get("name_of_papers");
            for (Object paper : papersArray) {
                papers.add((String) paper);
            }
            return true;
        }
        return false;
    }
    
}
