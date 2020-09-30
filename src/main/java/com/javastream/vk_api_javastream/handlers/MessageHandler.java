package com.javastream.vk_api_javastream.handlers;

import com.vk.api.sdk.objects.messages.Message;

/**
 * @author JavaStream on 30.09.2020
 */

public interface MessageHandler {
    Message handle(Message message) throws Exception;
}
