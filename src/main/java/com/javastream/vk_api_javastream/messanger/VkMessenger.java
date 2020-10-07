package com.javastream.vk_api_javastream.messanger;

import com.javastream.vk_api_javastream.utils.Util;
import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.GroupActor;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import com.vk.api.sdk.objects.messages.Message;
import com.vk.api.sdk.objects.photos.Photo;
import com.vk.api.sdk.objects.photos.PhotoUpload;
import com.vk.api.sdk.objects.photos.responses.PhotoUploadResponse;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.json.JSONObject;

import java.io.*;
import java.net.URL;
import java.util.List;

/**
 * @author JavaStream on 30.09.2020
 */

public class VkMessenger {
    private GroupActor groupActor;
    private VkApiClient vkApiClient;

    public VkMessenger() {
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

    public void sendMessage(String text, Integer peerId) throws ClientException {
        if (text == null || peerId == null) return;
        vkApiClient.messages()
                .send(groupActor)
                .peerId(peerId)
                .message(text)
                .randomId((int) (Math.random() * 2048))
                .executeAsRaw();
    }

    public void sendPhotoMessage(String text, File file, Message message) throws ClientException, ApiException, IOException {
        if (message == null) return;

        PhotoUpload uploadServer = vkApiClient.photos().getMessagesUploadServer(groupActor).execute();
        URL uploadUrl = uploadServer.getUploadUrl();

        JSONObject responce = Util.uploadFileToServer(uploadUrl, file);

        int serverJson = responce.getInt("server");
        String photo = responce.getString("photo");
        String hash = responce.getString("hash");

        List<Photo> photoList = vkApiClient.photos().saveMessagesPhoto(groupActor, photo).server(serverJson).hash(hash).execute();

        vkApiClient.messages()
                .send(groupActor)
                .peerId(message.getPeerId())
                .message(text)
                .attachment("photo" + photoList.get(0).getOwnerId() + "_" + photoList.get(0).getId())
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


/*
        PhotoUploadResponse photo = vkApiClient.upload().photo(uploadServer.getUploadUrl().toString(), file).execute();
        File coverFile = new File("D://1.jpg");
        OwnerCoverUploadResponse coverUploadResponse = new Upload(vkApiClient).photoOwnerCover(photoUpload.getUploadUrl().toString(), coverFile).execute();
        List<Photo> images = new Photos(vkApiClient).saveMessagesPhoto(groupActor, coverUploadResponse.getPhoto()).hash(coverUploadResponse.getHash()).server(205424).execute();
        vkApiClient.photos().saveMessagesPhoto(groupActor, photo.getHash());
        */