package com.rjil.cloud.tej.Common.datadriven.reader;



import com.rjil.cloud.tej.Common.datadriven.model.DataContainer;
import com.rjil.cloud.tej.Common.datadriven.model.TestDataRecord;
import com.rjil.cloud.tej.Common.datadriven.model.Value;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Scanner;

/**
 * Specify a way to read test data saved in Csv documents
 */
public class CSVDataReader extends TestDataReader {

    public static final String CSV_RESOULUTION = ".csv";
    public static final String COMMA_DELIMITER = ",";

    @Override
    protected String getResolution() {
        return CSV_RESOULUTION;
    }

    @Override
    protected DataContainer readTestData(URL testDataUrl) {

        DataContainer result = new DataContainer();

        try {
            URI uri = testDataUrl.toURI();
            File csvFile = new File(uri);
           // TestDataReader.logTestDataInfo(HeaderData.DataType.CSV.name(), csvFile.getAbsolutePath());
            Scanner scanner = new Scanner(csvFile);
            String[] headers = null;
            while (scanner.hasNext()) {
                String line = scanner.next();
                if (headers == null) {
                    headers = processHeaderLine(line);
                } else {
                    result.addTestData(processDataLine(line, headers));
                }
            }
        } catch (URISyntaxException e) {
            System.out.println(e.getMessage());
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }

        return result;
    }

    private TestDataRecord processDataLine(String line, String[] headers) {

        TestDataRecord result = new TestDataRecord();

        String[] values = line.split(COMMA_DELIMITER);

        for (int i = 0; i < headers.length; i++) {
            result.addValue(new Value(headers[i], values.length > i ? values[i] : ""));
        }

        return result;
    }

    private String[] processHeaderLine(String line) {
        return line.split(COMMA_DELIMITER);
    }
}
