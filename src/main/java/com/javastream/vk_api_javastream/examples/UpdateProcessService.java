package com.javastream.vk_api_javastream.examples;

import com.javastream.vk_api_javastream.messanger.VkMessenger;
import com.javastream.vk_api_javastream.utils.Util;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import com.vk.api.sdk.objects.messages.Message;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;

/**
 * @author JavaStream on 30.09.2020
 */


@Component
public class UpdateProcessService {

    // The main method of processing updates
    public void process(VkMessenger vkMessenger, Message message) throws ClientException, ApiException, IOException {

        //  I. SENDING TEXT MESSAGES

        // Send e text message to user (first way)
        if (message.getText().contains("Hello")) {
            vkMessenger.sendMessage("Hello my friend!", message);
        }

        // Send e text message to user (second way)
        if (message.getText().contains("Goodbye")) {
            int fromId = message.getFromId();
            vkMessenger.sendMessage("Goodbye my friend!", fromId);
        }



        //  II. CHECK SERVICE

        // Attachments check
        if (Util.hasAttachments(message)) { System.out.println("has Attachments"); }

        // Photo check
        if (Util.hasPhoto(message)) { System.out.println("has Photo");  }

        // Video check
        if (Util.hasVideo(message)) { System.out.println("has Video");  }

        // Audio check
        if (Util.hasAudio(message)) { System.out.println("has Audio");  }

        // Audio Records check
        if (Util.hasAudioMessage(message)) { System.out.println("has Audio Message");  }

        // Document check
        if (Util.hasDocument(message)) { System.out.println("has Document");  }

        // Link check
        if (Util.hasLink(message)) { System.out.println("has Link");  }

        // Text check
        if (Util.hasText(message)) { System.out.println("has Text");  }



        //  III. SENDING PHOTO MESSAGE (Text is optional and can be null)

        if (message.getText().contains("photo")) {
            File file = new File("D:\\1.jpg");
            vkMessenger.sendPhotoMessage("Hay, this is my first photo for you", file, message);
        }
    }
}
