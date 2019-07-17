package chat.controller;

import chat.entities.Message;
import chat.service.ChatService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "http://localhost:4200/chat")
@Controller
public class ChatController {
    private static final Logger logger = LoggerFactory.getLogger(ChatController.class);
    private ChatService chatService;

    @Autowired(required = true)
    @Qualifier(value = "chatService")
    public void setChatService(ChatService chatService) {
        this.chatService = chatService;
    }

    @MessageMapping("/meeting/{roomId}")
    @SendTo("/topic/room{roomId}")
    public Message greeting(@DestinationVariable String roomId, Message message) throws Exception {
        logger.info("11111" + message);
        return message;
    }

    @MessageMapping("/addNewUser/{roomId}")
    @SendTo("/topic/room{roomId}")
    public String addNewUser(@DestinationVariable String roomId, Message message, SimpMessageHeaderAccessor headerAccessor) throws Exception {
        logger.info("added new user" + message.toString());
        headerAccessor.getSessionAttributes().put("user", message.getAuthor());
        headerAccessor.getSessionAttributes().put("roomId", roomId);
        return this.chatService.addNewUser(message);
    }
}
