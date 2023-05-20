package FileOperations.CsvOperations;

import Model.ArticlePaper;
import Model.ConferencePaper;
import Model.Paper;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Random;

public class CsvGenerator {
    public void write(List<Paper> paperList) {
        try {
            File file = new File("papers.csv");
            if (file.length() != 0) {
                return;
            }

            FileWriter writer = new FileWriter("papers.csv");
            writingInForLoop(paperList, writer);
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void writingInForLoop(List<Paper> paperList, FileWriter writer) throws IOException {
        Random rand = new Random();

        for (Paper paperEntry : paperList) {

            if (paperEntry.getClass() == ConferencePaper.class) {
                writer.append("conference paper,");
                appendCommonInfo(paperEntry, writer);
                writer.append("bookTitle,").append (((ConferencePaper) paperEntry).getBookTitle()).append(",");
            } else if (paperEntry.getClass() == ArticlePaper.class) {
                writer.append("article paper,");
                appendCommonInfo(paperEntry, writer);
                writer.append("volume,").append(((ArticlePaper) paperEntry).getVolume()).append(",");
                writer.append("number,").append(((ArticlePaper) paperEntry).getNumber()).append(",");
                writer.append("journal,").append(((ArticlePaper) paperEntry).getJournal()).append(",");
            }
            int numberOfDownloads = rand.nextInt(1500);
            writer.append(String.valueOf(numberOfDownloads)).append(";");
            writer.append("\n");
        }
    }

    private void appendCommonInfo(Paper paper, FileWriter writer) throws IOException {
        writer.append("authors,").append(paper.getAuthors()).append(",");
        writer.append("title,").append(paper.getTitle()).append(",");
        writer.append("year,").append(paper.getYear()).append(",");
        writer.append("doi,").append(paper.getDOI()).append(",");
    }
}
