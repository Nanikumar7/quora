package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dtos.TopicResponseDto;
import com.example.demo.model.Topic;
import com.example.demo.service.TopicService;

@CrossOrigin("*")
@RestController
public class TopicController {

    @Autowired
    private TopicService topicService;

    @PostMapping("/topics")
    public Topic createTopic(@RequestBody Topic topic){//spring doesnt know how to extra name from json object 
        

       return  topicService.createTopic(topic.getName());
    }
    
    @GetMapping("/topics")
    public List<TopicResponseDto> getAllTopics(){
        return topicService.getAllTopics();
    }

}
