package CSVGenerator;

import Component.ArticlePaper;
import Component.ConferencePaper;
import Component.Paper;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Random;

public class WritePapersFile {
    public void write(List<Paper> paperList) {
        try {
            File file = new File("papers.csv");
            if (file.length()!=0) {
                return;
            }

            FileWriter writer = new FileWriter("papers.csv");
            writingInForLoop(paperList, writer);
            writer.flush();
            writer.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
}

    private void writingInForLoop(List<Paper> paperList, FileWriter writer) throws IOException {
        Random rand = new Random();
        
        for (Paper paperEntry: paperList) {

            if (paperEntry.getClass() == ConferencePaper.class) {
                writer.append("conference paper,");
                appendCommonInfo(paperEntry, writer);
                writer.append(((ConferencePaper) paperEntry).getBookTitle()).append(",");
            }

            else if (paperEntry.getClass() == ArticlePaper.class) {
                writer.append("article paper,");
                appendCommonInfo(paperEntry, writer);
                writer.append(((ArticlePaper) paperEntry).getVolume()).append(",");
                writer.append(((ArticlePaper) paperEntry).getNumber()).append(",");
                writer.append(((ArticlePaper) paperEntry).getJournal()).append(",");
            }
            int numberOfDownloads = rand.nextInt(1500);
            writer.append(String.valueOf(numberOfDownloads)).append(";");
            writer.append("\n");
        }
    }

    private void appendCommonInfo(Paper paper, FileWriter writer) throws IOException {
        writer.append(paper.getAuthors()).append(",");
        writer.append(paper.getTitle()).append(",");
        writer.append(paper.getYear()).append(",");
        writer.append(paper.getDOI()).append(",");
    }
}
