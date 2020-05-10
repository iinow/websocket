package com.ha.websocket.api.ws;

import com.ha.websocket.dto.SocketVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Slf4j
@Controller
public class SocketController {

    /**
     * registry.setApplicationDestinationPrefixes("/pub")
     * @MessageMapping(value = "/receive") -> /pub/receive
     * */
    @MessageMapping(value = "/receive")
    @SendTo(value = "/send")
    public SocketVO socketHandler(SocketVO socketVO) {
        return new SocketVO(socketVO.getUserName(), socketVO.getContent());
    }

//    @SubscribeMapping(value = "/send")
//    public void test(@Payload Object obj) {
//        log.info(obj.toString());
//    }
}
