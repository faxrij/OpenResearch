package Reader;

import java.io.*;
import java.util.Map;

import Component.ArticlePaper;
import Component.ConferencePaper;
import Component.Paper;
import org.jbibtex.*;

public class ReadFile {
    private void creatingPapers(String fileName, BibTeXEntry bibTeXEntry) {
        String authors = bibTeXEntry.getField(BibTeXEntry.KEY_AUTHOR).toUserString();

        String title = bibTeXEntry.getField(BibTeXEntry.KEY_TITLE).toUserString();

        String year = bibTeXEntry.getField(BibTeXEntry.KEY_YEAR).toUserString();
        String doi = bibTeXEntry.getField(BibTeXEntry.KEY_DOI).toUserString();

        if (fileName.startsWith("A")) {
            String volume = bibTeXEntry.getField(BibTeXEntry.KEY_VOLUME).toUserString();
            String number = bibTeXEntry.getField(BibTeXEntry.KEY_NUMBER).toUserString();
            String journal = bibTeXEntry.getField(BibTeXEntry.KEY_JOURNAL).toUserString();

            Paper articlePaper = new ArticlePaper(authors, title, year, volume, number, doi, journal);
        }

        else if (fileName.startsWith("IP")) {
            String bookTitle = bibTeXEntry.getField(BibTeXEntry.KEY_BOOKTITLE).toUserString();

            Paper conferencePaper = new ConferencePaper(authors, title, year, doi, bookTitle);
        }

    }

    public void read() {
        String folderPath = "Homework3"; // Replace with the path to your folder
        File folder = new File(folderPath);

        if (folder.isDirectory()) {
            File[] bibFiles = folder.listFiles((dir, name) -> name.endsWith(".bib"));
            assert bibFiles != null;
            for (File bibFile : bibFiles) {

                try (FileReader reader = new FileReader(bibFile)) {

                    BibTeXParser parser = new BibTeXParser();
                    BibTeXDatabase database = parser.parse(reader);
                    Map<Key, BibTeXEntry> entries = database.getEntries();

                    // Iterate over the entries and extract the name of each entry
                    for (Map.Entry<Key, BibTeXEntry> entry : entries.entrySet()) {

                        creatingPapers(bibFile.getName(), entry.getValue());
                    }
                }

                catch (IOException | ParseException e) {
                    System.err.println("Error parsing file " + bibFile.getName() + ": " + e.getMessage());
                }
            }
        } else {
            System.err.println("Not a directory: " + folderPath);
        }
    }
    }
