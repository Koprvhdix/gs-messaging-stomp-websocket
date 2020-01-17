package com.example.messagingstompwebsocket;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.WebSocketHandlerDecorator;
import org.springframework.web.socket.handler.WebSocketHandlerDecoratorFactory;

import java.security.Principal;

/**
 * @author Clockworkai
 * @since 2020/1/17
 */
@Component
public class WebSocketDecoratorFactory implements WebSocketHandlerDecoratorFactory {
    @Override
    public WebSocketHandler decorate(final WebSocketHandler handler) {
        return new WebSocketHandlerDecorator(handler) {
            @Override
            public void afterConnectionEstablished(final WebSocketSession session) throws Exception {
                final Principal principal = session.getPrincipal();
                if (principal != null) {
                    SocketManager.add(principal.getName(), session);
                }

                super.afterConnectionEstablished(session);
            }

            @Override
            public void afterConnectionClosed(final WebSocketSession session, final CloseStatus closeStatus) throws Exception {
                final Principal principal = session.getPrincipal();
                if (principal != null) {
                    // 身份校验成功，移除socket连接
                    SocketManager.remove(principal.getName());
                }
                super.afterConnectionClosed(session, closeStatus);
            }
        };
    }
}
