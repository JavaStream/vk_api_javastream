package com.javastream.vk_api_javastream.examples;

import com.javastream.vk_api_javastream.Vk_Starter;
import com.vk.api.sdk.exceptions.ClientException;
import com.vk.api.sdk.objects.messages.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author JavaStream on 30.09.2020
 */


@Component
public class UpdateProcessService {

    @Autowired private Vk_Starter vk_starter;

    // The main method of processing updates
    public void process(Message message) throws ClientException {
        if (message.getText().contains("Hello")) {
            vk_starter.getVkMessenger().sendMessage("Hello my friend!", message);
        }

        if (message.getText().contains("Goodbye")) {
            vk_starter.getVkMessenger().sendMessage("Goodbye my friend!", message);        }
    }

}
