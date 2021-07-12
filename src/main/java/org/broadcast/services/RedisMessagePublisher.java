package org.broadcast.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.broadcast.models.Message;
import org.broadcast.models.MessageResponse;
import org.broadcast.models.MessageType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class RedisMessagePublisher implements MessagePublisher {
    private static final Logger logger = LoggerFactory.getLogger(RedisMessagePublisher.class);

    @Autowired
    private final RedisTemplate<String, Object> redisTemplate;

    @Autowired
    private final ChannelTopic topic;

    public RedisMessagePublisher(final RedisTemplate<String, Object> redisTemplate, final ChannelTopic topic) {
        this.redisTemplate = redisTemplate;
        this.topic = topic;
    }

    public MessageResponse publish(final Message message) {
        logger.info("Writing message to Redis: {}", message);

        ObjectMapper mapper = new ObjectMapper();
        try {
            redisTemplate.convertAndSend(topic.getTopic(), mapper.writeValueAsString(message));
        } catch (Exception e) {
            e.printStackTrace();
            return new MessageResponse(message.getId(), false, MessageType.RESPONSE);
        }

        return new MessageResponse(message.getId(), true, MessageType.RESPONSE);
    }
}
