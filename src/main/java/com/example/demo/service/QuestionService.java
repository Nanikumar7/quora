package com.example.demo.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dtos.QuestionDto;
import com.example.demo.dtos.QuestionResponseDto;
import com.example.demo.dtos.SearchQuestionRequestDto;
import com.example.demo.dtos.SearchQuestionResponseDto;
import com.example.demo.dtos.TopicDto;
import com.example.demo.dtos.UserDto;
import com.example.demo.model.Question;
import com.example.demo.model.Topic;
import com.example.demo.model.User;
import com.example.demo.repository.QuestionRepository;
import com.example.demo.repository.TopicRepository;
import com.example.demo.repository.UserRepository;

@Service
public class QuestionService {

    @Autowired
    QuestionRepository questionRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TopicRepository topicRepository;


    public QuestionResponseDto postNewQuestion(QuestionDto questionDto) {

        Question question = new Question();

        Optional<User> user = userRepository.findById(questionDto.getUserId());
        
        if(user.isPresent()){
            question.setUser(user.get());
        } else{
            return null;
        }       

        question.setTitle(questionDto.getTitle());

        question.setBody(questionDto.getBody());
        question.setCreated_at(new Date());
        List<Topic> topics = new ArrayList<>();

        for(Long t:questionDto.getTopicIds() ){
           Optional<Topic> topicRecords= topicRepository.findById(t);
           if(topicRecords.isPresent()){
                 topics.add(topicRecords.get());
           }
        }
        question.setTopicIds(topics);

        questionRepository.save(question);
        QuestionResponseDto response = new QuestionResponseDto();

        response.setId(question.getId());
        response.setTitle(question.getTitle());
        response.setBody(question.getBody());
        
        List<Long> tIds = new ArrayList<>();
        for(Topic tt: topics){
             tIds.add(tt.getId());
        }
      response.setTopicIds(tIds);
        return response;
    }




    public List<SearchQuestionResponseDto> searchQuestions(SearchQuestionRequestDto filter){

       String body= filter.getText();
       String title =filter.getTag();
       if(body==null || body.isBlank() && title == null || title.isBlank()){

        return new ArrayList();
        
       }

       List<Question> questions= questionRepository.findByTextOrTag(body,title);   
       
       
       
       List<SearchQuestionResponseDto> dtoList = new ArrayList<>();

       for(Question q : questions){
            SearchQuestionResponseDto searchQuesResDto = new SearchQuestionResponseDto();
            searchQuesResDto.setId(q.getId());
            searchQuesResDto.setTitle(q.getTitle());
            searchQuesResDto.setBody(q.getBody());
            // searchQuesResDto.setTopicIds(q.getTopicIds());
            searchQuesResDto.setCreated_at(q.getCreated_at());
            // searchQuesResDto.setUser(q.getUser());
            dtoList.add(searchQuesResDto);

            //set User
            if(q.getUser()!=null){
                User user = q.getUser();
                UserDto u = new UserDto();
                u.setId(user.getId());
                u.setUserName(user.getUserName());
                u.setEmail(user.getEmail());
                u.setBio(user.getBio());
                searchQuesResDto.setUser(u);
            }

            //set Topic
            List<TopicDto> tFin = new ArrayList<>();
            if(q.getTopicIds()!=null){
                List<Topic> topicList= q.getTopicIds();
                for(Topic t:topicList ){
                    TopicDto tDto = new TopicDto();
                    tDto.setId(t.getId());
                    tDto.setName(t.getName());
                    tFin.add(tDto);
                }
                searchQuesResDto.setTopicDto(tFin);

            }
        
       }
      
       return dtoList;
    }



    

}


















