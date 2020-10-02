package com.javastream.vk_api_javastream.utils;

import com.javastream.vk_api_javastream.model.Attachment;
import com.javastream.vk_api_javastream.model.AttachmentType;
import com.vk.api.sdk.objects.audio.Audio;
import com.vk.api.sdk.objects.base.Link;
import com.vk.api.sdk.objects.docs.Doc;
import com.vk.api.sdk.objects.messages.AudioMessage;
import com.vk.api.sdk.objects.messages.Message;
import com.vk.api.sdk.objects.messages.MessageAttachment;
import com.vk.api.sdk.objects.messages.MessageAttachmentType;
import com.vk.api.sdk.objects.photos.Photo;
import com.vk.api.sdk.objects.photos.PhotoSizes;
import com.vk.api.sdk.objects.video.Video;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

public class Util {

    public static List<Attachment> getAttachments(List<MessageAttachment> attachments) {
        if (attachments.size() == 0) return null;

        List<Attachment> listAtachments = new ArrayList<>();

        for (MessageAttachment attach : attachments) {
            Attachment attachment = new Attachment();
            MessageAttachmentType type = attach.getType();

            if (type.getValue().equals("photo")) {
                Photo photo = attach.getPhoto();
                attachment.setAccessKey(photo.getAccessKey());
                attachment.setContentId(photo.getId());
                attachment.setType(AttachmentType.PHOTO);
                List<PhotoSizes> sizes = photo.getSizes();
                PhotoSizes photoSizes = sizes.get(sizes.size() - 1);
                attachment.setUrl(photoSizes.getUrl());
                listAtachments.add(attachment);
            }

            if (type.getValue().equals("video")) {
                Video video = attach.getVideo();
                attachment.setAccessKey(video.getAccessKey());
                attachment.setContentId(video.getId());
                attachment.setType(AttachmentType.VIDEO);
                listAtachments.add(attachment);
            }

            if (type.getValue().equals("audio")) {
                Audio audio = attach.getAudio();
                attachment.setContentId(audio.getId());
                attachment.setType(AttachmentType.AUDIO);
                listAtachments.add(attachment);
            }

            if (type.getValue().equals("audio_message")) {
                AudioMessage audioMessage = attach.getAudioMessage();
                attachment.setAccessKey(audioMessage.getAccessKey());
                attachment.setContentId(audioMessage.getId());
                attachment.setType(AttachmentType.AUDIO_MESSAGE);
                listAtachments.add(attachment);
            }

            if (type.getValue().equals("doc")) {
                Doc doc = attach.getDoc();
                attachment.setAccessKey(doc.getAccessKey());
                attachment.setContentId(doc.getId());
                attachment.setType(AttachmentType.DOCUMENT);
                listAtachments.add(attachment);
            }

            if (type.getValue().equals("link")) {
                Link link = attach.getLink();
                attachment.setType(AttachmentType.LINK);
                listAtachments.add(attachment);
            }
        }

        return listAtachments;
    }

    public static boolean hasAttachments(Message message) {
        return message.getAttachments().size() > 0;
    }

    public static boolean hasPhoto(Message message) {
        List<Attachment> attachments = getAttachments(message.getAttachments());
        if (attachments == null || attachments.size() == 0) return false;

        for (Attachment attachment : attachments) {
            if (attachment.getType().equals(AttachmentType.PHOTO)) return true;
        }

        return false;
    }

    public static boolean hasVideo(Message message) {
        List<Attachment> attachments = getAttachments(message.getAttachments());
        if (attachments == null || attachments.size() == 0) return false;

        for (Attachment attachment : attachments) {
            if (attachment.getType().equals(AttachmentType.VIDEO)) return true;
        }

        return false;
    }

    public static boolean hasAudio(Message message) {
        List<Attachment> attachments = getAttachments(message.getAttachments());
        if (attachments == null || attachments.size() == 0) return false;

        for (Attachment attachment : attachments) {
            if (attachment.getType().equals(AttachmentType.AUDIO)) return true;
        }

        return false;
    }

    public static boolean hasAudioMessage(Message message) {
        List<Attachment> attachments = getAttachments(message.getAttachments());
        if (attachments == null || attachments.size() == 0) return false;

        for (Attachment attachment : attachments) {
            if (attachment.getType().equals(AttachmentType.AUDIO_MESSAGE)) return true;
        }

        return false;
    }

    public static boolean hasDocument(Message message) {
        List<Attachment> attachments = getAttachments(message.getAttachments());
        if (attachments == null || attachments.size() == 0) return false;

        for (Attachment attachment : attachments) {
            if (attachment.getType().equals(AttachmentType.DOCUMENT)) return true;
        }

        return false;
    }

    public static boolean hasLink(Message message) {
        List<Attachment> attachments = getAttachments(message.getAttachments());
        if (attachments == null || attachments.size() == 0) return false;

        for (Attachment attachment : attachments) {
            if (attachment.getType().equals(AttachmentType.LINK)) return true;
        }

        return false;
    }

    public static boolean hasText(Message message) {
        return !message.getText().isEmpty();
    }
}
