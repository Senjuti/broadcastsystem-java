package org.broadcast.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RedisMessageSubscriber implements MessageListener {
    public static List<String> messageList = new ArrayList<>();

    private static final Logger logger = LoggerFactory.getLogger(RedisMessagePublisher.class);
    private static final String TAG = "[Subscriber]";

    public void onMessage(final Message message, byte[] pattern) {
        messageList.add(message.toString());
        logger.info("{} Message received: {}", TAG, message);
    }
}
