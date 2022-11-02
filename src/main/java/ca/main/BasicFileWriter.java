package ca.main;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class BasicFileWriter {
    private static final String RESOURCE = "MyNew.txt";

    private String getPathToResources() throws URISyntaxException {
        URL resource = getClass().getClassLoader().getResource(RESOURCE);

        return new File(resource.toURI()).getAbsolutePath();
    }

    public void writeCSVFile() {
        String[] header = {"Name", "ID", "Date", "UserID"};
        String inpath = null;
        try {
            inpath = getPathToResources();
        } catch (URISyntaxException e) {
            e.printStackTrace();
            return;
        }

        String outpath = inpath.replace(RESOURCE, "raw-out.csv");

        try (FileWriter writer = new FileWriter(outpath)) {
            writer.write(String.join(",", header) + "\n");

            try (InputStream is = new FileInputStream(inpath)) {
                try (Scanner sc = new Scanner(is, StandardCharsets.UTF_8)) {
                    while (sc.hasNextLine()) {
                        writer.write(sc.nextLine() + "\n");
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
