package com.ha.websocket.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
@AllArgsConstructor
public class SocketVO {
    private String userName;
    private String content;
}
