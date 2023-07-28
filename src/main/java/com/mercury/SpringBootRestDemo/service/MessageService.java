package com.mercury.SpringBootRestDemo.service;
import com.mercury.SpringBootRestDemo.bean.Message;
import com.mercury.SpringBootRestDemo.dao.MessageDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MessageService {

    @Autowired
    private MessageDao messageDao;

    public List<Message> getAllMessages() {
        return messageDao.findAll();
    }

    public Message addMessage(Message message) {
        return messageDao.save(message);
    }

    public Message updateMessage(int id, String answer) {
        Optional<Message> optionalMessage = messageDao.findById(id);
        if (optionalMessage.isPresent()) {
            Message existingMessage = optionalMessage.get();
            existingMessage.setAnswer(answer);
            return messageDao.save(existingMessage);  // Ensure this returns the full updated message object
        } else {
            return null;
        }
    }

}
