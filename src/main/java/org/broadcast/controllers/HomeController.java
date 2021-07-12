package org.broadcast.controllers;

import org.broadcast.models.Message;
import org.broadcast.models.MessageResponse;
import org.broadcast.services.RedisMessagePublisher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
    private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
    private static final String TAG = "[Controller]";

    @Autowired
    private RedisMessagePublisher redisMessagePublisher;

    @RequestMapping(value = "/msg", produces = "application/json", method = RequestMethod.POST)
    public MessageResponse sendMessage(@RequestBody Message message) {
        logger.info("{} POST /msg {}", TAG, message);
        return redisMessagePublisher.publish(message);
    }
}