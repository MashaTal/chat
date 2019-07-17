package chat.component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;


@Component
public class WebSocketEventListener {
    @Autowired
    private SimpMessageSendingOperations messagingTemplate;
    private static final Logger logger = LoggerFactory.getLogger(WebSocketEventListener.class);

    @EventListener
    public void onSessionConnected(SessionConnectEvent event) {
        logger.info("Received a new web socket connection");
    }

    @EventListener
    public void onSessionDisConnected(SessionDisconnectEvent event) {
        StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());

        String username = (String) headerAccessor.getSessionAttributes().get("user");
        String roomId = (String) headerAccessor.getSessionAttributes().get("roomId");
        logger.info("Received a new web socket disconnection " + roomId);

        String disconnect = "user " + username + " disconnected!";
        messagingTemplate.convertAndSend("/topic/room" + roomId, disconnect);
    }

}
