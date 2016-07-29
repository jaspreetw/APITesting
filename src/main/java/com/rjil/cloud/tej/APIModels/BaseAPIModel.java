package com.rjil.cloud.tej.APIModels;

/**
 * Base API Model.
 */
public class BaseAPIModel {
    public String authToken;
    public String userId;

    public String getAuthToken() {
        return authToken;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
