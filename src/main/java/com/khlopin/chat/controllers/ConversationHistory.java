package com.khlopin.chat.controllers;


import com.khlopin.chat.entity.Message;
import com.khlopin.chat.services.DBRepository;
import lombok.SneakyThrows;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
@RestController
public class ConversationHistory {


   @GetMapping("/history")
   @CrossOrigin("*")
   @SneakyThrows
    protected List<Message> sendHistory() {
       return DBRepository.getMessageList();
    }



}
