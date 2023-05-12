package Component;

public abstract class Paper {
    private final String authors;
    private final String title;
    private final String year;
    private final String DOI;

    public Paper(String authors, String title, String year, String DOI) {
        this.authors = authors;
        this.title = title;
        this.year = year;
        this.DOI = DOI;
    }

    public String getAuthors() {
        return authors;
    }

    public String getTitle() {
        return title;
    }

    public String getYear() {
        return year;
    }

    public String getDOI() {
        return DOI;
    }
}
