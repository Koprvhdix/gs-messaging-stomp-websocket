package com.example.messagingstompwebsocket;

import org.springframework.web.socket.WebSocketSession;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Clockworkai
 * @since 2020/1/17
 */
public final class SocketManager {
    private static final ConcurrentHashMap<String, WebSocketSession> MANAGER = new ConcurrentHashMap<>();

    private SocketManager() {
    }

    public static void add(final String key, final WebSocketSession webSocketSession) {
        MANAGER.put(key, webSocketSession);
    }

    public static void remove(final String key) {
        MANAGER.remove(key);
    }

    public static WebSocketSession get(final String key) {
        return MANAGER.get(key);
    }

    public static Integer getNumber() {
        return MANAGER.size();
    }

}
