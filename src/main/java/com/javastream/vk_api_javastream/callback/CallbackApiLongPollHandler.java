package com.javastream.vk_api_javastream.callback;

import com.javastream.vk_api_javastream.handlers.MessageHandler;
import com.vk.api.sdk.callback.longpoll.CallbackApiLongPoll;
import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.GroupActor;
import com.vk.api.sdk.objects.messages.Message;
import lombok.SneakyThrows;

/**
 * @author JavaStream on 30.09.2020
 */

public class CallbackApiLongPollHandler extends CallbackApiLongPoll {

    private MessageHandler handler;

    public CallbackApiLongPollHandler(VkApiClient client, GroupActor actor, MessageHandler handler) {
        super(client, actor);
        this.handler = handler;
    }


    @SneakyThrows
    @Override
    public void messageNew(Integer groupId, Message message) {
        if (message != null && !message.isOut()) {
            if (handler == null ) return;
            handler.handle(message);
        }
    }

    @SneakyThrows
    @Override
    public void messageNew(Integer groupId, String secret, Message message) {
        if (message != null && !message.isOut()) {
            if (handler == null ) return;
            handler.handle(message);
        }
    }
}
