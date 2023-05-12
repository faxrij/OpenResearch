package Component;

public class ConferencePaper extends Paper{
    private final String bookTitle;
    public ConferencePaper(String authors, String title, String year, String DOI, String bookTitle) {
        super(authors, title, year, DOI);
        this.bookTitle = bookTitle;
    }

    public String getBookTitle() {
        return bookTitle;
    }
}
