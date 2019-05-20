package com.lianup.spikesystem.service;

import java.util.Map;

//@Service
public interface MsgSendService {
    /**
     * 发送消息
     * @param message
     */
    public void sendMessage(final Map<String, Object> message);

}
