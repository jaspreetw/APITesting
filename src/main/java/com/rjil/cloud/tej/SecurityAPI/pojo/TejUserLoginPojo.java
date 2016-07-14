package com.rjil.cloud.tej.SecurityAPI.pojo;

import java.util.HashMap;

import com.google.gson.Gson;

public class TejUserLoginPojo {

	String authProviderId;
	String emailId;
	String password;
	String deviceType;
	HashMap<String, String> deviceInfo;
	String deviceName;
	String deviceKey;
	String registrationId;
	String imei;
	String serial;
	String platformType;
	String platformVersion;
	String apiLevel;
	String clientVersion;
	String brand;
	String model;
	String manufacturer;
	String product;
	String sreenSize;
	String screenResolution;
	String screenDensity;
	
	public String getDeviceName() {
		return deviceName;
	}
	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}
	public String getDeviceKey() {
		return deviceKey;
	}
	public void setDeviceKey(String deviceKey) {
		this.deviceKey = deviceKey;
	}
	public String getRegistrationId() {
		return registrationId;
	}
	public void setRegistrationId(String registrationId) {
		this.registrationId = registrationId;
	}
	public String getImei() {
		return imei;
	}
	public void setImei(String imei) {
		this.imei = imei;
	}
	public String getSerial() {
		return serial;
	}
	public void setSerial(String serial) {
		this.serial = serial;
	}
	public String getPlatformType() {
		return platformType;
	}
	public void setPlatformType(String platformType) {
		this.platformType = platformType;
	}
	public String getPlatformVersion() {
		return platformVersion;
	}
	public void setPlatformVersion(String platformVersion) {
		this.platformVersion = platformVersion;
	}
	public String getApiLevel() {
		return apiLevel;
	}
	public void setApiLevel(String apiLevel) {
		this.apiLevel = apiLevel;
	}
	public String getClientVersion() {
		return clientVersion;
	}
	public void setClientVersion(String clientVersion) {
		this.clientVersion = clientVersion;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public String getManufacturer() {
		return manufacturer;
	}
	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}
	public String getProduct() {
		return product;
	}
	public void setProduct(String product) {
		this.product = product;
	}
	public String getSreenSize() {
		return sreenSize;
	}
	public void setSreenSize(String sreenSize) {
		this.sreenSize = sreenSize;
	}
	public String getScreenResolution() {
		return screenResolution;
	}
	public void setScreenResolution(String screenResolution) {
		this.screenResolution = screenResolution;
	}
	public String getScreenDensity() {
		return screenDensity;
	}
	public void setScreenDensity(String screenDensity) {
		this.screenDensity = screenDensity;
	}
	public HashMap<String, String> getDeviceInfo() {
		return deviceInfo;
	}
	public void setDeviceInfo(HashMap<String, String> deviceInfo) {
		this.deviceInfo = deviceInfo;
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
	
	public static String getGsonFromObject(TejUserLoginPojo request) {
		Gson gson = new Gson();
		 return gson.toJson(request);
	}
}
