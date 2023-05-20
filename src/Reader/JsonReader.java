package Reader;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.simple.*;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import java.io.IOException;

public class JsonReader {
    private static final String JSON_FILE_PATH = "reading-list.json";

    public List<String> getReadingListNamesForResearcher(String researcherName) {
        JSONParser parser = new JSONParser();
        List<String> readingListNames = new ArrayList<>();

        try (FileReader fileReader = new FileReader(JSON_FILE_PATH)) {
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

        try (FileReader fileReader = new FileReader(JSON_FILE_PATH)) {
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

    @SuppressWarnings("unchecked")
    public void removePaperFromReadingList(String readingListName, String paperName) {
        JSONParser parser = new JSONParser();

        try (FileReader fileReader = new FileReader(JSON_FILE_PATH)) {
            JSONArray jsonArray = (JSONArray) parser.parse(fileReader);

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
            try (FileWriter fileWriter = new FileWriter(JSON_FILE_PATH)) {
                fileWriter.write(jsonArray.toJSONString());
                fileWriter.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }

    public void addReadingList(String researcher, String readingListName) {
        JSONParser parser = new JSONParser();

        try (FileReader reader = new FileReader(JSON_FILE_PATH)) {
            JSONArray jsonArray = (JSONArray) parser.parse(reader);

            Random rand = new Random();
            String readingListId = String.valueOf(rand.nextInt(1000));

            JSONObject newReadingList = new JSONObject();
            newReadingList.put("number_of_papers", 0);
            newReadingList.put("readinglist_id", readingListId);
            newReadingList.put("readinglist_name", readingListName);
            newReadingList.put("name_of_papers", new JSONArray());
            newReadingList.put("creator_researcher_name", researcher);

            jsonArray.add(newReadingList);

            try (FileWriter writer = new FileWriter(JSON_FILE_PATH)) {
                ObjectMapper mapper = new ObjectMapper();
                mapper.writeValue(writer, jsonArray);
            }

            System.out.println("Reading list added successfully.");
        } catch (ParseException | IOException e) {
            e.printStackTrace();
        }
    }
}