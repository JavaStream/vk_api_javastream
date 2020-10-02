package com.javastream.vk_api_javastream.model;

import lombok.Data;

import java.net.URL;

@Data
public class Attachment {
    private AttachmentType type;
    private int contentId;
    private String accessKey;
    private URL url;
}
