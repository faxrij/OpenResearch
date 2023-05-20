package FileOperations.JsonOperations;

import org.json.JSONArray;
import org.json.JSONException;

public class JsonChecker {
    public boolean exists(JSONArray nameOfPapers, String paperName) {
        boolean paperExists = false;
        try {
            paperExists = checking(nameOfPapers, paperName, paperExists);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return paperExists;
    }

    private boolean checking(JSONArray nameOfPapers, String paperName, boolean paperExists) throws JSONException {
        for (int j = 0; j < nameOfPapers.length(); j++) {
            String existingPaperName = nameOfPapers.getString(j);
            if (existingPaperName.equals(paperName)) {
                paperExists = true;
                break;
            }
        }
        return paperExists;
    }
}
