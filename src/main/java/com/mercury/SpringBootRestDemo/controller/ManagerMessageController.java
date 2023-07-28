package com.mercury.SpringBootRestDemo.controller;


import com.mercury.SpringBootRestDemo.bean.Message;
import com.mercury.SpringBootRestDemo.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/manager/messages")
public class ManagerMessageController {

    @Autowired
    private MessageService messageService;

    @GetMapping
    public List<Message> getAllMessages() {
        return messageService.getAllMessages();
    }

    @PutMapping(value = "/{id}", consumes = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<Message> updateMessage(@PathVariable int id, @RequestBody String answer){

    Message existingMessage = messageService.updateMessage(id, answer);
        if(existingMessage != null) {
            return ResponseEntity.ok(existingMessage);  // This should now include the original message as well as the updated answer
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
