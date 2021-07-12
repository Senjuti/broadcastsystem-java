package org.broadcast.models;

import lombok.Data;

@Data
public class HistoryRequest {
    private int history;

    public HistoryRequest(int history) {
        this.history = history;
    }

    public int getHistory() {
        return history;
    }

    public void setHistory(int history) {
        this.history = history;
    }

    @Override
    public String toString() {
        return "HistoryRequest{" +
                "history=" + history +
                '}';
    }
}
