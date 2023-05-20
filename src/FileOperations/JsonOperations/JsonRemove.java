package FileOperations.JsonOperations;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class JsonRemove {
    private static final String JSON_FILE_PATH = "reading-list.json";

    public void removePaperFromReadingList(String readingListName, String paperName) {

        try {
            JSONArray jsonArray = commonMethod();
            for (Object json : jsonArray) {
                JSONObject jsonObject = (JSONObject) json;
                String listName = (String) jsonObject.get("readinglist_name");

                if (listName.equals(readingListName)) {
                    JSONArray papersArray = (JSONArray) jsonObject.get("name_of_papers");
                    papersArray.remove(paperName);
                    jsonObject.put("number_of_papers", papersArray.size());
                    break;
                }
            }

            // Write the updated JSON back to the file with formatted output
            updateJson(jsonArray);
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }

    private void updateJson(JSONArray jsonArray) {
        try (FileWriter fileWriter = new FileWriter(JSON_FILE_PATH)) {
            fileWriter.write(jsonArray.toJSONString());
            fileWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private JSONArray commonMethod() throws IOException, ParseException {
        JSONParser parser = new JSONParser();

        FileReader fileReader = new FileReader(JSON_FILE_PATH);
        return (JSONArray) parser.parse(fileReader);
    }
}
