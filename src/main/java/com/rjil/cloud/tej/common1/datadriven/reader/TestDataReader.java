package com.rjil.cloud.tej.common1.datadriven.reader;


import com.rjil.cloud.tej.common1.datadriven.model.DataContainer;

import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;

/**
 * Specify a way to read test data
 */
public abstract class TestDataReader {

    public static final String FILE_PREFIX = "file:/";
    public static final String SLASH_SIGN = "/";

/*    */

    /**
     * Reads test data based on the class of concrete test
     *
     * @return {@link
     * @see
     *//*
    public  DataContainer readTestData(Class<T> clazz) throws ParseException {
        String name = clazz.getSimpleName();
        URL testDataUrl = clazz.getResource(SLASH_SIGN + name + getResolution());
        return readTestData(testDataUrl);
    }*/
    public DataContainer readTestData(String path) throws ParseException {
        URL fileUrl = null;
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                fileUrl = new URL(FILE_PREFIX + path);
            } else {
                fileUrl = new URL(FILE_PREFIX + "/" + path);
            }

        } catch (MalformedURLException e) {

        }
        return readTestData(fileUrl);
    }

    /**
     * Returns a resolution of the file with test data
     *
     * @return {@link String}
     */
    protected abstract String getResolution();

    /**
     * Reads test data from the given URL
     *
     * @param testDataUrl an url of test data
     * @return {@link
     * @see
     */
    protected abstract DataContainer readTestData(URL testDataUrl) throws ParseException;


}
