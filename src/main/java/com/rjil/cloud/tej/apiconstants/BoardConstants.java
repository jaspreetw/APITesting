package com.rjil.cloud.tej.apiconstants;

/**
 * Board API Constants
 */
public class BoardConstants {

    public static String getBoardName() {
        return "$.boardName";
    }

    public static String getBoardType() {
        return "$.boardType";
    }

    public static String getBoardPermission() {
        return "$.boardPermission";
    }

    public static String getMemberDetails() {
        return "$.memberDetails";
    }

    public static String getUserId() {
        return "$.memberDetails.userId";
    }

    public static String getPhoneNo() {
        return "$.memberDetails.phoneNo";
    }

    public static String getEmailId() {
        return "$.memberDetails.emailId";
    }

    public static String getMemberType() {
        return "$.memberDetails.memberType";
    }

    public static String getFileDetails() {
        return "$.fileDetails";
    }

    public static String getObjectKey() {
        return "$.fileDetails.objectKey";
    }

    public static String getStatus() {
        return "$.fileDetails.status";
    }

    public static String getSourceFolder() {
        return "$.fileDetails.sourceFolder";
    }

    public static String getAvailableOffLine() {
        return "$.availableOffLine";
    }

}
