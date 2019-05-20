package com.lianup.spikesystem.service.impl;

import com.lianup.spikesystem.service.MsgSendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class MsgSendServiceImpl implements MsgSendService {

    @Autowired
    private JmsMessagingTemplate messagingTemplate;

    private static final String DESC = "GOODS_QUEUE";

    @Override
    public void sendMessage(Map<String, Object> message){
        {
            messagingTemplate.convertAndSend(DESC, message);
        }
    }
}
