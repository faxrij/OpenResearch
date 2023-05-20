package Reader;

import Component.ArticlePaper;
import Component.ConferencePaper;
import Component.Paper;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CsvReader {
    private final static String CSV_FILE_PATH = "papers.csv";

    public List<Paper> parseCSV() {
        return parseCsvFile();
    }

    private static List<Paper> parseCsvFile() {
        List<Paper> papers = new ArrayList<>();

        try {
            BufferedReader reader = new BufferedReader(new FileReader(CSV_FILE_PATH));
            String line;

            while ((line = reader.readLine()) != null) {
                Paper paper = parseCsvLine(line);
                if (paper != null) {
                    papers.add(paper);
                }
            }

            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return papers;
    }

    private static Paper parseCsvLine(String line) {
        String[] csvParts = line.split(",");

        String paperType = csvParts[0];
        String authors = getValue(csvParts, "authors");
        String title = getValue(csvParts, "title");
        String year = getValue(csvParts, "year");
        String doi = getValue(csvParts, "doi");
        String volume = getValue(csvParts, "volume");
        String number = getValue(csvParts, "number");
        String journal = getValue(csvParts, "journal");
        String bookTitle = getValue(csvParts, "bookTitle");


        if (paperType.equals("article paper")) {
            return new ArticlePaper(authors, title, year, doi, volume, number, journal);
        } else if (paperType.equals("conference paper")) {
            return new ConferencePaper(authors, title, year, doi, bookTitle);
        }

        return null;
    }

    private static String getValue(String[] csvParts, String keyword) {
        for (int i = 1; i < csvParts.length; i += 1) {
            if (csvParts[i].equals(keyword)) {
                return csvParts[i + 1];
            }
        }
        return null;
    }

}

