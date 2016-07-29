package com.rjil.cloud.tej.APIModels;

import com.google.gson.Gson;
import com.rjil.cloud.tej.APIhelpers.BaseTestScript;
import com.rjil.cloud.tej.Common.Utils;

/**
 * Login API Model Class
 */
public class LoginModel extends BaseAPIModel {

    public String authProviderId;
    public String emailId;
    public String loginId;
    public String password;
    public String deviceType;
    private DeviceInfoModel deviceInfo;

    public LoginModel() {
        if(BaseTestScript.isIdam){
            loginId= BaseTestScript.serverConfig.get("Email");
        }else{
            emailId= BaseTestScript.serverConfig.get("Email");
        }
        password= BaseTestScript.serverConfig.get("Password");
    }


    /**
     * Method to get JSON string form Model
     * @return : JSON String
     */
    public String getGsonFromObject() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }

    public String getAuthProviderId() {
        return authProviderId;
    }

    public void setAuthProviderId(String authProviderId) {
        this.authProviderId = authProviderId;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    public DeviceInfoModel getDeviceInfo() {
        return deviceInfo;
    }

    public void setDeviceInfo(DeviceInfoModel deviceInfo) {
        this.deviceInfo = deviceInfo;
    }
}
