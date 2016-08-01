package com.rjil.cloud.tej.Common.datadriven.reader;


import com.rjil.cloud.tej.Common.datadriven.model.DataContainer;
import com.rjil.cloud.tej.Common.datadriven.model.TestDataRecord;
import com.rjil.cloud.tej.Common.datadriven.model.Value;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;

import java.io.File;
import java.io.FileReader;
import java.net.URI;
import java.net.URL;

/**
 * Specify a way to read test data saved in Json documents
 */
public class JSONDataReader extends TestDataReader {

    public static final String JSON_RESOLUTION = ".js";

    @Override
    protected String getResolution() {
        return JSON_RESOLUTION;
    }

    @Override
    public DataContainer readTestData(URL testDataUrl) {
        DataContainer result = new DataContainer();

        JSONParser parser = new JSONParser();
        try {
            URI uri = testDataUrl.toURI();
            File file = new File(uri);
            FileReader fileReader = new FileReader(file);
            JSONArray jsonArray = (JSONArray) parser.parse(fileReader);
            for (Object aJsonArray : jsonArray) {
                String data = "record";
                TestDataRecord record = new TestDataRecord();
                record.addValue(new Value(data, aJsonArray));
                result.addTestData(record);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return result;
    }
}
