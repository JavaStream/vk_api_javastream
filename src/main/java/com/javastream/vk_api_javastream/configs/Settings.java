package com.javastream.vk_api_javastream.configs;

/**
 * @author JavaStream on 30.09.2020
 */


public class Settings {
    public static String accessToken;
    public static int groupID;

    public static String getAccessToken() {
        return accessToken;
    }

    public static void setAccessToken(String accessToken) {
        Settings.accessToken = accessToken;
    }

    public static int getGroupID() {
        return groupID;
    }

    public static void setGroupID(String groupID) {
        Settings.groupID = Integer.parseInt(groupID);
    }
}
