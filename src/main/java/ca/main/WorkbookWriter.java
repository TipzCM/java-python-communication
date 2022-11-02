package ca.main;

import com.aspose.cells.SaveFormat;
import com.aspose.cells.TxtLoadOptions;
import com.aspose.cells.Workbook;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Collections;

public class WorkbookWriter {
    private static final String RESOURCE = "MyNew.txt";

    private String getPathToResources() throws URISyntaxException {
        URL resource = getClass().getClassLoader().getResource(RESOURCE);

        return new File(resource.toURI()).getAbsolutePath();
    }

    public void writeWorkbookCSV() {
        try {
            String inpath = getPathToResources();
            String outPath = inpath.replace(RESOURCE, "my-workbook.csv");

            Workbook workbook = new Workbook(inpath);

            workbook.save(outPath, SaveFormat.CSV);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
}
