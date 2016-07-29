package com.rjil.cloud.tej.APIModels;

import com.google.gson.Gson;

/**
 * File Upload API Model
 */
public class FileUploadModel {
	String name;
	Double size;
	String hash;
	String description;
	String folderKey;
	String boardKey;
	Boolean isProfilePic;
	Boolean isAutoUpload;
	Boolean isContactPhoto;
	String sourceFolder;
	LocationModel location;
	String[] keywords;
	Boolean hidden;

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Double getSize() {
		return size;
	}
	public void setSize(Double size) {
		this.size = size;
	}
	public String getHash() {
		return hash;
	}
	public void setHash(String hash) {
		this.hash = hash;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getFolderKey() {
		return folderKey;
	}
	public void setFolderKey(String folderKey) {
		this.folderKey = folderKey;
	}
	public String getBoardKey() {
		return boardKey;
	}
	public void setBoardKey(String boardKey) {
		this.boardKey = boardKey;
	}
	public Boolean getIsProfilePic() {
		return isProfilePic;
	}
	public void setIsProfilePic(Boolean isProfilePic) {
		this.isProfilePic = isProfilePic;
	}
	public Boolean getIsAutoUpload() {
		return isAutoUpload;
	}
	public void setIsAutoUpload(Boolean isAutoUpload) {
		this.isAutoUpload = isAutoUpload;
	}
	public Boolean getIsContactPhoto() {
		return isContactPhoto;
	}
	public void setIsContactPhoto(Boolean isContactPhoto) {
		this.isContactPhoto = isContactPhoto;
	}
	public String getSourceFolder() {
		return sourceFolder;
	}
	public void setSourceFolder(String sourceFolder) {
		this.sourceFolder = sourceFolder;
	}
	public String[] getKeywords() {
		return keywords;
	}
	public void setKeywords(String[] keywords) {
		this.keywords = keywords;
	}
	public Boolean getHidden() {
		return hidden;
	}
	public void setHidden(Boolean hidden) {
		this.hidden = hidden;
	}
	public LocationModel getLocation() {
		return location;
	}

	public void setLocation(LocationModel location) {
		this.location = location;
	}


	public static String getGsonFromObject(FileUploadModel request) {
		Gson gson = new Gson();
		 return gson.toJson(request);
	}

}
