package Reader;


import org.json.simple.*;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class JsonReader {
    public List<String> getReadingListNamesForResearcher(String researcherName) {
        JSONParser parser = new JSONParser();
        List<String> readingListNames = new ArrayList<>();

        try (FileReader fileReader = new FileReader("reading-list.json")) {
            Object obj = parser.parse(fileReader);
            JSONArray jsonArray = (JSONArray) obj;

            for (Object json : jsonArray) {
                JSONObject jsonObject = (JSONObject) json;
                String creatorName = (String) jsonObject.get("creator_researcher_name");
                String readingListName = (String) jsonObject.get("readinglist_name");

                if (creatorName.equals(researcherName)) {
                    readingListNames.add(readingListName);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return readingListNames;
    }

    public List<String> getPapersOfReadingList(String readingListName) {
        List<String> papers = new ArrayList<>();
        JSONParser parser = new JSONParser();

        try (FileReader fileReader = new FileReader("reading-list.json")) {
            JSONArray jsonArray = (JSONArray) parser.parse(fileReader);

            for (Object json : jsonArray) {
                JSONObject jsonObject = (JSONObject) json;
                String listName = (String) jsonObject.get("readinglist_name");

                if (listName.equals(readingListName)) {
                    JSONArray papersArray = (JSONArray) jsonObject.get("name_of_papers");
                    for (Object paper : papersArray) {
                        papers.add((String) paper);
                    }
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return papers;
    }
}
