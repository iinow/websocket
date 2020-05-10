package com.ha.websocket.dto;

import com.ha.websocket.common.MessageType;
import lombok.Data;

@Data
public class SampleMessage {
    private MessageType type;
    private String content;
    private String sender;
}
