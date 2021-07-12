package org.broadcast.models;

import lombok.Data;

@Data
public class MessageResponse {
    private long id;
    private boolean success;
    private MessageType messageType;

    public MessageResponse(long id, boolean success, MessageType messageType) {
        this.id = id;
        this.success = success;
        this.messageType = messageType;
    }

    public MessageType getMessageType() {
        return messageType;
    }

    public void setMessageType(MessageType messageType) {
        this.messageType = messageType;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "MessageResponse{" +
                "id=" + id +
                ", success=" + success +
                ", messageType=" + messageType +
                '}';
    }
}
