package chat.service;

import chat.entities.Message;
import org.springframework.stereotype.Service;

@Service
public class ChatService {
    public String addNewUser(Message message) {
        return message.getAuthor() + " joined!";
    }
}
