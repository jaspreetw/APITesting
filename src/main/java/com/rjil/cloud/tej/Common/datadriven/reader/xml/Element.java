package com.rjil.cloud.tej.Common.datadriven.reader.xml;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * A model for element tag in xml test data for data driven tests
 */
@XmlRootElement(name = "element")
public class Element {

    private String name;
    private String value;

    @XmlAttribute(name = "name")
    public String getName() {
        return name;
    }

    @XmlAttribute(name = "value")
    public String getValue() {
        return value;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
