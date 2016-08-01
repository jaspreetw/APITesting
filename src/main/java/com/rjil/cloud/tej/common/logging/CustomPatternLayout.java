/*
 * Copyright Medtronic, Inc. 2013
 *
 * MEDTRONIC CONFIDENTIAL - This document is the property of Medtronic,
 * Inc.,and must be accounted for. Information herein is confidential. Do
 * not reproduce it, reveal it to unauthorized persons, or send it outside
 * Medtronic without proper authorization.
 */

package com.rjil.cloud.tej.common.logging;

import org.apache.log4j.PatternLayout;

import java.util.Map;

/**
 * Custom Pattern layout class allows to output events into a txt document,
 * adjust content of each log record,
 * add additional header information
 */
public class CustomPatternLayout extends PatternLayout {

    private Map<String, String> headerData;

    public CustomPatternLayout(final String pattern, final Map<String, String> headerData) {
        super(pattern);
        this.headerData = headerData;
    }

    @Override
    public String getHeader() {

        StringBuilder builder = new StringBuilder();
        for(String key : headerData.keySet()){
            builder.append(key).append(":");
            int i = key.length()<35 ? 35 - key.length() : 0;
            char character = (char) i;
            builder.append(character == '\0' ? ' ' : character);
            builder.append(headerData.get(key));
            builder.append(System.getProperty("line.separator"));
        }
        builder.append(System.getProperty("line.separator"));
        return builder.toString();
    }
}