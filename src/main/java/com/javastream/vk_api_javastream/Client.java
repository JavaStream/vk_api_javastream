package com.javastream.vk_api_javastream;

import com.javastream.vk_api_javastream.configs.Settings;

/**
 * @author JavaStream on 30.09.2020
 */

public class Client {

    public Client(int groupID, String accessToken) {
        Settings.accessToken = accessToken;
        Settings.groupID = groupID;
    }

    public VkStarter initVkService() {
        return new VkStarter();
    }

}
