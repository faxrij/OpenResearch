package Component;

public abstract class Paper {
    private String authors;
    private String title;
    private String year;
    private String DOI;

    public Paper(String authors, String title, String year, String DOI) {
        this.authors = authors;
        this.title = title;
        this.year = year;
        this.DOI = DOI;
    }

    public Paper() {
        this.authors = null;
        this.title = null;
        this.year = null;
        this.DOI = null;
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

    public void setAuthors(String authors) {
        this.authors = authors;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public void setDOI(String DOI) {
        this.DOI = DOI;
    }
    @Override
    public String toString() {
        return title;
    }
}
