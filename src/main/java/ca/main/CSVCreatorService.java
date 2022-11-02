package ca.main;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvValidationException;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.util.Scanner;

public class CSVCreatorService {

    private static final String RESOURCE = "MyNew.csv";

    private String getPathToResources() throws URISyntaxException {
        URL resource = getClass().getClassLoader().getResource(RESOURCE);

        return new File(resource.toURI()).getAbsolutePath();
    }

    public void writeCSV() {
        String pathToResources;
        try {
            pathToResources = getPathToResources();
        } catch (URISyntaxException e) {
            e.printStackTrace();
            return;
        }

        String outPath = pathToResources.replace(RESOURCE, "my-new-csv.csv");

        try (CSVWriter writer = new CSVWriter(new FileWriter(outPath))) {
            String[] header = {"Name", "ID", "Date", "UserID"};

            // write header
            writer.writeNext(header);

            // write all the other lines
            try (CSVReader reader = new CSVReader(new FileReader(pathToResources))) {
                String[] line;
                while ((line = reader.readNext()) != null) {
                    writer.writeNext(line);
                }
            } catch (IOException | CsvValidationException e) {
                e.printStackTrace();
                System.out.println(e.getMessage());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
