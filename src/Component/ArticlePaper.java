package Component;

public class ArticlePaper extends Paper {
    private String volume;
    private String number;
    private String journal;

    public void setVolume(String volume) {
        this.volume = volume;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public void setJournal(String journal) {
        this.journal = journal;
    }

    public ArticlePaper(String authors, String title, String year, String volume, String number, String DOI, String journal) {
        super(authors, title, year, DOI);
        this.volume = volume;
        this.journal = journal;
        this.number = number;
    }

    public ArticlePaper(String volume, String number, String journal) {
        this.volume = volume;
        this.number = number;
        this.journal = journal;
    }


    public String getVolume() {
        return volume;
    }

    public String getNumber() {
        return number;
    }

    public String getJournal() {
        return journal;
    }
}
