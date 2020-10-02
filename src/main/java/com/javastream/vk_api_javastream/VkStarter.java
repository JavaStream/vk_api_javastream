package com.javastream.vk_api_javastream;

import com.javastream.vk_api_javastream.callback.CallbackApiLongPollHandler;
import com.javastream.vk_api_javastream.configs.Settings;
import com.javastream.vk_api_javastream.handlers.MessageHandler;
import com.javastream.vk_api_javastream.messanger.VkMessenger;
import com.vk.api.sdk.client.TransportClient;
import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.GroupActor;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import com.vk.api.sdk.httpclient.HttpTransportClient;

/**
 * @author JavaStream on 30.09.2020
 */

public class VkStarter {

    private static VkApiClient vkApiClient;
    private static GroupActor groupActor;
    public MessageHandler messageHandler = null;
    private static VkMessenger vkMessenger;
    private final TransportClient transportClient = new HttpTransportClient();

    public void startUpdates(MessageHandler messageHandler) {
        this.messageHandler = messageHandler;
        groupActor = init();
        vkMessenger = new VkMessenger();
        vkMessenger.setGroupActor(groupActor);
        vkMessenger.setVkApiClient(vkApiClient);

        try {
            if (!vkApiClient.groups().getLongPollSettings(groupActor, groupActor.getGroupId()).execute().getIsEnabled()) {
                vkApiClient.groups().setLongPollSettings(groupActor, groupActor.getGroupId()).enabled(true).messageNew(true).wallPostNew(true).execute();
            }


            CallbackApiLongPollHandler handler = new CallbackApiLongPollHandler(vkApiClient, groupActor, messageHandler);
            handler.run();
        } catch (ClientException | ApiException ex ) {
            System.out.println("Restart connection to VK API..");
            startUpdates(messageHandler);
        }
    }


    public GroupActor init() {
        int groupID = getGroupID();
        String accessToken = getAccessToken();

        vkApiClient = new VkApiClient(this.transportClient);
        return new GroupActor(groupID, accessToken);
    }

    public VkMessenger getVkMessenger() {
        return vkMessenger;
    }

    private static String getAccessToken() {
        return Settings.getAccessToken();
    }

    private static Integer getGroupID() {
        return Settings.getGroupID();
    }
}
