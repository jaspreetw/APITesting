/*
* Copyright Medtronic, Inc. 2013
*
* MEDTRONIC CONFIDENTIAL - This document is the property of Medtronic,
* Inc.,and must be accounted for. Information herein is confidential. Do
* not reproduce it, reveal it to unauthorized persons, or send it outside
* Medtronic without proper authorization.
*/

package com.rjil.cloud.tej.Common.logging.level;

import org.apache.log4j.Level;

/**
 * The PASS level designates pass events that are logged in verification statements when verification is passed
 */
public class PassLevel extends Level {

    public static final int PASS_INT = WARN_INT + 20;

    public static final Level PASS = new PassLevel(PASS_INT, "PASS", 10);

    protected PassLevel(int levelIntValue, String name, int agr)
    {
        super(levelIntValue, name, agr);
    }
}
