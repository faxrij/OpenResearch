package Component;

import java.util.List;

public class ReadingList {
    private List<Paper> paperList;

    public ReadingList(List<Paper> paperList) {
        this.paperList = paperList;
    }

    public List<Paper> getPaperList() {
        return paperList;
    }

    public void setPaperList(List<Paper> paperList) {
        this.paperList = paperList;
    }
}
