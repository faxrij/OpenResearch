package Component;

public class ArticlePaper extends Paper{
    private final String volume;
    private final String number;
    private final String journal;

    public ArticlePaper(String authors, String title, String year, String volume, String number, String DOI, String journal) {
        super(authors, title, year, DOI);
        this.volume = volume;
        this.journal=journal;
        this.number=number;
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
