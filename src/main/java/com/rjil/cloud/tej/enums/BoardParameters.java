package com.rjil.cloud.tej.enums;

/**
 * Login Parameter Enum
 */
public enum BoardParameters {
    XUSERID("X-User-Id"),
    XDEVICEKEY("X-Device-Key"),
    BOARDNAME("boardName"),
    BOARDTYPE("boardType"),
    MEMBERDETAILS("memberDetails"),
    USERID("userId"),
    PHONENO("phoneNo"),
    EMAILID("emailId"),
    MEMBERTYPE("memberType"),
    OBJECTKEY("objectKey"),
    STATUS("status"),
    SOURCEFOLDER("sourceFolder"),
    BOARDPERMISSION("boardPermission"),
    BOARDKEY("boardKey"),
    BOARDSELFLINK("boardSelfLink"),
    BOARDFOLDERKEY("boardFolderKey"),
    AVAILABLEOFFLINE("availableOffLine"),
    BOARDTYPEDESC("boardTypeDesc"),
    LASTUPDATEDDATE("lastUpdatedDate"),
    CREATEDDATE("createdDate"),
    MEMBERCOUNT("memberCount"),
    FILECOUNT("fileCount"),
    COMMENTCOUNT("commentCount"),
    IMAGECOUNT("imageCount"),
    DOCCOUNT("docCount"),
    AUDIOCOUNT("audioCount"),
    VIDEOCOUNT("videoCount"),
    BOARDOWNERPIC("boardOwnerPic"),
    BOARDCONTENTSIZE("boardContentSize"),
    BOARDCOVERPIC("boardCoverPic"),
    BOARDOWNERID("boardOwnerId"),
    OWNERPROFILENAME("ownerProfileName"),
    LIKECOUNT("likeCount"),
    FILEDETAILS("fileDetails"),
	AUTHORIZATION("Authorization"),
	ACCEPTLANGUAGE("Accept-Language");

    public String value;

    BoardParameters(String value) {
        this.value = value;

    }

    public String getValue() {
        return value;
    }
}
