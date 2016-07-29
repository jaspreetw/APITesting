package com.rjil.cloud.tej.Common.datadriven.reader;



import com.rjil.cloud.tej.Common.datadriven.model.DataContainer;
import com.rjil.cloud.tej.Common.datadriven.model.TestDataRecord;
import com.rjil.cloud.tej.Common.datadriven.model.Value;
import com.rjil.cloud.tej.Common.datadriven.reader.xml.Element;
import com.rjil.cloud.tej.Common.datadriven.reader.xml.Record;
import com.rjil.cloud.tej.Common.datadriven.reader.xml.TestData;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;

/**
 * Specify a way to read test data saved in Xml documents
 */
public class XMLDataReader extends TestDataReader {

    public static final String XML_RESOLUTION = ".xml";

    @Override
    protected String getResolution() {
        return XML_RESOLUTION;
    }

    @Override
    public DataContainer readTestData(URL testDataUrl) {
        DataContainer result = new DataContainer();

        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(TestData.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            URI uri = testDataUrl.toURI();
            File file = new File(uri);
           // TestDataReader.logTestDataInfo(HeaderData.DataType.XML.name(), file.getAbsolutePath());
            TestData testData = (TestData) unmarshaller.unmarshal(file);
            List<Record> records = testData.getRecords();
            for (Record record : records) {
                TestDataRecord dataRecord = new TestDataRecord();
                List<Element> elements = record.getElements();
                for (Element element : elements) {
                    dataRecord.addValue(new Value(element.getName(), element.getValue()));
                }
                result.addTestData(dataRecord);
            }
        } catch (JAXBException e) {
            System.out.println(e.toString());
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        return result;
    }
}
