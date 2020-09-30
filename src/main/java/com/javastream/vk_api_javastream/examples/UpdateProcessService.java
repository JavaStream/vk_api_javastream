package com.javastream.vk_api_javastream.examples;

import com.javastream.vk_api_javastream.VkStarter;
import com.vk.api.sdk.exceptions.ClientException;
import com.vk.api.sdk.objects.messages.Message;
import org.springframework.stereotype.Component;

/**
 * @author JavaStream on 30.09.2020
 */


@Component
public class UpdateProcessService {

    // The main method of processing updates
    public void process(VkStarter vkStarter, Message message) throws ClientException {
        if (message.getText().contains("Hello")) {
            vkStarter.getVkMessenger().sendMessage("Hello my friend!", message);
        }

        if (message.getText().contains("Goodbye")) {
            vkStarter.getVkMessenger().sendMessage("Goodbye my friend!", message);        }
    }
}
