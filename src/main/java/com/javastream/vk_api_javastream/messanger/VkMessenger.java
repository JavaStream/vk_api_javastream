package com.javastream.vk_api_javastream.messanger;

import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.GroupActor;
import com.vk.api.sdk.exceptions.ClientException;
import com.vk.api.sdk.objects.messages.Message;
import org.springframework.stereotype.Component;

/**
 * @author JavaStream on 30.09.2020
 */

@Component
public class VkMessenger {
    private GroupActor groupActor;
    private VkApiClient vkApiClient;

    public VkMessenger(){
    }

    public void sendMessage(String text, Message message) throws ClientException {
        if (message == null) return;
        vkApiClient.messages()
                .send(groupActor)
                .peerId(message.getPeerId())
                .message(text)
                .randomId((int) (Math.random() * 2048))
                .executeAsRaw();
    }

    public void setGroupActor(GroupActor groupActor) {
        this.groupActor = groupActor;
    }

    public void setVkApiClient(VkApiClient vkApiClient) {
        this.vkApiClient = vkApiClient;
    }
}
