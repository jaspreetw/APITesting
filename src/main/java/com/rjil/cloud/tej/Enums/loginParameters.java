package com.rjil.cloud.tej.Enums;

/**
 * Login Parameter Enum
 */
public enum loginParameters {
    AUTHPROVIDERID("authProviderId"),
    EMAILID("emailId"),
    LOGINID("loginId"),
    PASSWORD("password"),
    DEVICETYPE("deviceType"),
    DEVICENAME("deviceName"),
    DEVICEKEY("deviceKey"),
    REGISTRATIONID("registrationId"),
    IMEI("imei"),
    SERIAL("serial"),
    PLATFORMTYPE("platformType"),
    PLATFORMVERSION("platformVersion"),
    APILEVEL("apiLevel"),
    CLIENTVERSION("clientVersion"),
    BRAND("brand"),
    TYPE("type"),
    MODEL("model");

    public String value;

    loginParameters(String value) {
        this.value = value;

    }

    public String getValue() {
        return value;
    }
}
