package ca.main;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Properties;

public class App {

    private static final String SCRIPT_NAME = "main.py";

    private static String PYTHON_PATH = "";
    private static String SCRIPT_PATH = "";

    private static class ResourceFileReader {
        public void readProps() {
            URL url = getClass()
                    .getClassLoader()
                    .getResource("prog.properties");

            if (url == null) {
                throw new IllegalArgumentException("File prog.properties does not exist. Please create this in resources");
            }

            File file = new File(url.getFile());

            try (FileReader reader = new FileReader(file)) {
                Properties p = new Properties();

                p.load(reader);

                PYTHON_PATH = p.getProperty("python_path");
                SCRIPT_PATH = p.getProperty("script_path");

                System.out.println(p.getProperty("test"));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                System.out.println(e.getMessage());
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println(e.getMessage());
            }

//            try (FileReader reader = new FileReader("prog.properties")) {
//                Properties p = new Properties();
//                p.load(reader);
//
//                PYTHON_PATH = p.getProperty("python_path");
//                SCRIPT_PATH = p.getProperty("script_path");
//            } catch (FileNotFoundException e) {
//                System.out.println(e.getMessage());
//                System.out.println("File not found. Please create a prog.properties file.");
//                e.printStackTrace();
//            } catch (IOException e) {
//                System.out.println(e.getMessage());
//                e.printStackTrace();
//            }
        }
    }

    private static void setProperties() {
        ResourceFileReader rsf = new ResourceFileReader();
        rsf.readProps();
    }

    public static void main(String[] args) {
        System.out.println("Starting....");

        setProperties();

        Runner runner = new Runner();

        int exitCode = -1;
        try {
            exitCode = runner.runScript(
                    PYTHON_PATH,
                    SCRIPT_PATH + SCRIPT_NAME
            );
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        System.out.println("Completed: " + exitCode);
    }
}
