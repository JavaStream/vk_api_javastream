package com.javastream.vk_api_javastream.examples;

import com.javastream.vk_api_javastream.VkStarter;
import com.javastream.vk_api_javastream.messanger.VkMessenger;
import com.javastream.vk_api_javastream.model.Attachment;
import com.javastream.vk_api_javastream.utils.Util;
import com.vk.api.sdk.exceptions.ClientException;
import com.vk.api.sdk.objects.messages.Message;
import com.vk.api.sdk.objects.messages.MessageAttachment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author JavaStream on 30.09.2020
 */


@Component
public class UpdateProcessService {

    // The main method of processing updates
    public void process(VkMessenger vkMessenger, Message message) throws ClientException {

        // Attachment check
        if (Util.hasAttachments(message)) { System.out.println("has Attachments"); }

        // Photo check
        if (Util.hasPhoto(message)) { System.out.println("has Photo");  }

        // Video check
        if (Util.hasVideo(message)) { System.out.println("has Video");  }

        // Audio check
        if (Util.hasAudio(message)) { System.out.println("has Audio");  }

        // Audio Message check
        if (Util.hasAudioMessage(message)) { System.out.println("has Audio Message");  }

        // Document check
        if (Util.hasDocument(message)) { System.out.println("has Document");  }

        // Link check
        if (Util.hasLink(message)) { System.out.println("has Link");  }

        // Text check
        if (Util.hasText(message)) { System.out.println("has Text");  }



        if (message.getText().contains("Hello")) {
            vkMessenger.sendMessage("Hello my friend!", message);
        }

        if (message.getText().contains("Goodbye")) {
            int fromId = message.getFromId();
            vkMessenger.sendMessage("Goodbye my friend!", fromId);
        }

        if (message.getText().contains("photo")) {
            vkMessenger.sendPhotoMessage("Goodbye my friend!", message);
        }
    }
}
