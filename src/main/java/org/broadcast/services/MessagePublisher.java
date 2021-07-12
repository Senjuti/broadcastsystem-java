package org.broadcast.services;

import org.broadcast.models.Message;

public interface MessagePublisher {
    void publish(final Message message);
}
