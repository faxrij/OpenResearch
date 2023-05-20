package FileOperations.CsvOperations;

import java.io.*;
import java.util.Arrays;
import java.util.List;

public class CsvUpdate {
    private final static String filePath = "papers.csv";

    public void updateCSV(String title) {
        try {
            // Read the CSV file
            File inputFile = new File(filePath);
            File tempFile = new File("temp.csv");
            BufferedReader reader = new BufferedReader(new FileReader(inputFile));
            BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));
            String line;

            reading(title, reader, writer);

            reader.close();
            writer.close();

            // Replace the original file with the temporary file
            inputFile.delete();
            tempFile.renameTo(inputFile);

            System.out.println("CSV file updated successfully.");

        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + filePath);
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Error reading/writing file: " + filePath);
            e.printStackTrace();
        }
    }

    private void reading(String title, BufferedReader reader, BufferedWriter writer) throws IOException {
        String line;
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(",");
            List<String> list = Arrays.asList(parts);
            boolean containsString = list.contains(title);
            if (containsString) {
                // Get download amount in the line
                String downloadNum = parts[parts.length - 1];
                downloadNum = downloadNum.substring(0, downloadNum.length() - 1);

                int value = Integer.parseInt(downloadNum);
                // Increment the value
                value++;
                // Update the line with the incremented value
                parts[parts.length - 1] = value + ";";
            }
            // Write the updated line to the temporary file
            updating(writer, parts);
        }
    }

    private void updating(BufferedWriter writer, String[] parts) throws IOException {
        writer.write(String.join(",", parts));
        writer.newLine();
    }

}
