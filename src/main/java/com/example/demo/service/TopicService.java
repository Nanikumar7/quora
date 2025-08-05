package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dtos.TopicResponseDto;
import com.example.demo.model.Topic;
import com.example.demo.repository.TopicRepository;

@Service
public class TopicService {

    @Autowired
    private TopicRepository topicRepository;

    public Topic createTopic(String name){
//save method expects entire object not a single field- solution is to create objecct or native query
        Topic topic = new Topic();
        topic.setName(name);
       return  topicRepository.save(topic);
    }

    public List<TopicResponseDto> getAllTopics(){
        List<Topic> topics = topicRepository.findAll();
        List<TopicResponseDto> responseTopics = new ArrayList<>();
        
        for(Topic t: topics ){
            TopicResponseDto responseTopic = new TopicResponseDto();
           responseTopic.setId(t.getId());
           responseTopic.setName(t.getName());
           responseTopics.add(responseTopic);
        }
        return responseTopics;
      }
}
