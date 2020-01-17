package com.example.messagingstompwebsocket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.util.HtmlUtils;

/**
 * @Author: Clockworkai
 * @Date: 2020/1/7 16:42
 */
@RestController
public class TestController {

    @Autowired
    SimpMessagingTemplate template;

    @RequestMapping("/test")
    public Greeting test() {
//        WebSocketSession webSocketSession = SocketManager.get("test");
//        if (webSocketSession != null) {
//            /**
//             * 主要防止broken pipe
//             */
//            template.convertAndSendToUser("test", "/queue/sendUser", "您好");
//        }
//
        template.convertAndSend("/topic/greetings", new Greeting("Hello, " + HtmlUtils.htmlEscape("测试") + "!"));
//
//        SocketManager.get("test").getAsyncRemote().sendText(message)
        return new Greeting("Hello, " + HtmlUtils.htmlEscape("测试") + "!");
    }

    @RequestMapping("/test2")
    public Greeting test2() {
        template.convertAndSend("/user/test/topic/greetings", new Greeting("Hello, " + HtmlUtils.htmlEscape("测试") + "!"));
        template.convertAndSendToUser("test", "/topic/greetings", new Greeting("Hello, " + HtmlUtils.htmlEscape("测试") + "!"));
        return new Greeting("Hello, " + HtmlUtils.htmlEscape("测试") + "!");
    }
}
