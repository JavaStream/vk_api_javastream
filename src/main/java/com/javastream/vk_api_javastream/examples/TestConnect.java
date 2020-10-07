package com.javastream.vk_api_javastream.examples;

import com.javastream.vk_api_javastream.Client;
import com.javastream.vk_api_javastream.VkStarter;
import com.javastream.vk_api_javastream.handlers.MessageHandler;
import com.javastream.vk_api_javastream.messanger.VkMessenger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;

/**
 * @author JavaStream on 30.09.2020
 */

@Component
public class TestConnect {

    @Autowired private UpdateProcessService updateProcessService;

    private MessageHandler messageHandler = null;

    private void start() {
        int groupId = 170690651;
        String accessToken = "bbb072f080b094e1c47248b5c694187497714f55e6296e35c253833cb0266316847d0b6273500aefb6fff";

        Client client = new Client(groupId, accessToken);
        VkStarter vkStarter = client.initVkService();
        VkMessenger vkMessenger = client.getVkMessanger(vkStarter);


        // Работа с сообщениями в группе
        messageHandler = message -> {
            updateProcessService.process(vkMessenger, message);
            return message;
        };

        vkStarter.startUpdates(messageHandler);
    }


    @PostConstruct
    private void run() {
        start();
    }
}
