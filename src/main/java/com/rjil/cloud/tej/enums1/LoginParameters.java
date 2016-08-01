package com.rjil.cloud.tej.enums1;

/**
 * Login Parameter Enum
 */
public enum LoginParameters {
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

    LoginParameters(String value) {
        this.value = value;

    }

    public String getValue() {
        return value;
    }
}
