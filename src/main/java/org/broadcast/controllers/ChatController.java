package org.broadcast.controllers;

import org.broadcast.config.ConfigValues;
import org.broadcast.dao.MessageDAO;
import org.broadcast.models.HistoryRequest;
import org.broadcast.models.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class ChatController {
    private static final Logger logger = LoggerFactory.getLogger(ChatController.class);
    private static final String TAG = "[ChatController]";

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @Autowired
    MessageDAO messageDAO;

    @MessageMapping(ConfigValues.GET_MSG_URL)
    @SendTo(ConfigValues.TOPIC_CHAT_URL)
    public Message incomingRequest(HistoryRequest history) throws Exception {
        logger.info("{} Incoming Request in websocket {} ", TAG, history);

        if(history.getHistory() == 1) {
            System.out.println(messageDAO.getNumberOfMessages());
            List<Object> allMessages = messageDAO.getAllMessages();
            for(Object message : allMessages) {
                firechat((Message)message);
            }
        }
        return null;
    }

    public void firechat(Message message) {
        logger.info("{} Sending message to Websocket {} ", TAG, message);
        simpMessagingTemplate.convertAndSend(ConfigValues.TOPIC_CHAT_URL, message);
    }

}
