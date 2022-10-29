package ca.main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URISyntaxException;

public class Runner {

    // alternative way to call python
    public int runScript1(String pythonPath, String pathToScript) throws IOException, InterruptedException, URISyntaxException {
        java.lang.ProcessBuilder pb = new ProcessBuilder(
                pythonPath,
                pathToScript,
                "Hello World"
        ).inheritIO();

        Process p = pb.start();

        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(p.getOutputStream()));
        BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));

        BufferedReader r = new BufferedReader(reader);
        String line;
        while ((line = r.readLine()) != null) {
            System.out.println("I'm coming from java : " + line);
        }


        return p.waitFor();
    }


    public int runScript(String pythonPath, String pathToScript) throws IOException, InterruptedException, URISyntaxException {

        Process p = Runtime.getRuntime().exec(pythonPath + " " + pathToScript);

        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(p.getOutputStream()));
        BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));

        BufferedReader r = new BufferedReader(reader);

        String line;
        while ((line = r.readLine()) != null) {
            System.out.println("I'm coming from java : " + line);
        }


        return p.waitFor();
    }
}
