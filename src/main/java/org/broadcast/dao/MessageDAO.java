package org.broadcast.dao;

import org.broadcast.config.ConfigValues;
import org.broadcast.models.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MessageDAO {
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    public void addMessage(Message message) {
        redisTemplate.persist(ConfigValues.REDIS_PERSIST_KEY);
        redisTemplate.opsForList().rightPush(ConfigValues.REDIS_PERSIST_KEY, message);
    }

    public long getNumberOfMessages() {
        return redisTemplate.opsForList().size(ConfigValues.REDIS_PERSIST_KEY);
    }

    public List<Object> getAllMessages() {
        return redisTemplate.opsForList().range(ConfigValues.REDIS_PERSIST_KEY, 0, getNumberOfMessages());
    }
}
