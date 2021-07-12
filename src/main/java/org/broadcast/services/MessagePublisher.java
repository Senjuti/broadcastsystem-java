package org.broadcast.services;

import org.broadcast.models.Message;
import org.broadcast.models.MessageResponse;

public interface MessagePublisher {
    MessageResponse publish(final Message message);
}
