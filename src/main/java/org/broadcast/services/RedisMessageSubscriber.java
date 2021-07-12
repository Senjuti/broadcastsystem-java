package org.broadcast.services;

import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RedisMessageSubscriber implements MessageListener {
    public static List<String> messageList = new ArrayList<>();

    public void onMessage(final Message message, byte[] pattern) {
        messageList.add(message.toString());
        System.out.println("Message received: " + Arrays.toString(message.getBody()));
    }
}
