/*
* Copyright Medtronic, Inc. 2013
*
* MEDTRONIC CONFIDENTIAL - This document is the property of Medtronic,
* Inc.,and must be accounted for. Information herein is confidential. Do
* not reproduce it, reveal it to unauthorized persons, or send it outside
* Medtronic without proper authorization.
*/

package com.rjil.cloud.tej.common1.logging.level;

import org.apache.log4j.Level;

/**
 * The FAIL level designates fail events that are logged in verification statements when verification is failed
 */
public class FailLevel extends Level {

    public static final int FAIL_INT = ERROR_INT;

    public static final Level FAIL = new PassLevel(FAIL_INT, "FAIL", 10);

    protected FailLevel(int levelIntValue, String name, int agr)
    {
        super(levelIntValue, name, agr);
    }
}
