package com.example.demo.service;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dtos.AnswerDto;
import com.example.demo.dtos.AnswerResponseDto;
import com.example.demo.dtos.QuestionResponseDto;
import com.example.demo.dtos.UserDto;
import com.example.demo.model.Answer;
import com.example.demo.model.Question;
import com.example.demo.model.User;
import com.example.demo.repository.AnswerRepository;
import com.example.demo.repository.QuestionRepository;
import com.example.demo.repository.TopicRepository;
import com.example.demo.repository.UserRepository;

@Service
public class AnswerService {

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TopicRepository topicRepository;

    @Autowired
    private AnswerRepository answerRepository;


    public AnswerResponseDto postAnswerToQuestion(Long questionId,AnswerDto answerDto){
        
        
        Optional<Question> question =questionRepository.findById(questionId);
        Optional<User> user=userRepository.findById(answerDto.getUserId());

        if(question.isEmpty() ||user.isEmpty() ){
                    return  null;
        }

        Answer answer = new Answer();

        answer.setText(answerDto.getText());

        answer.setQuestion(question.get());
        answer.setAnswerUser(user.get());
        answer.setCreated_at(new Date());

        Answer result= answerRepository.save(answer);

        AnswerResponseDto resp = new AnswerResponseDto();

        resp.setId(result.getId());
        resp.setText(result.getText());
        resp.setCreated_at(result.getCreated_at());
        // resp.setQues(question.get());
        QuestionResponseDto qDtoResp = new QuestionResponseDto();
        qDtoResp.setId(question.get().getId());
        qDtoResp.setTitle(question.get().getTitle());
        qDtoResp.setBody(question.get().getBody());
       
        resp.setQues(qDtoResp);

        // List<Topic> tt = new ArrayList<>();
        // for(Topic tIds: question.get().getTopicIds()){
        //     if(tIds!=null){
        //         tt.add(tIds);
        //     }
        // }
        

        // resp.setUser(user.get());
        UserDto u = new UserDto();
        u.setId(user.get().getId());
        u.setUserName(user.get().getUserName());
        u.setEmail(user.get().getEmail());
        u.setBio(user.get().getBio());
        resp.setUser(u);

        return resp;
    }





    public AnswerResponseDto updateAnswer(Long answerId, String text){

        Optional<Answer> currentAnswer =answerRepository.findById(answerId);

        if(currentAnswer.isPresent()){
         
           if(text!=null || !text.isBlank()){
            //    Answer updatedAnswer = new Answer(); ---> cause null pointer exception when u try to use  updatedResAnswer.getQuestion().getId()
            //sol;
            Answer updatedAnswer = currentAnswer.get();
                updatedAnswer.setText(text);
                Answer updatedResAnswer = answerRepository.save(updatedAnswer);

            AnswerResponseDto res = new AnswerResponseDto();
            res.setId(updatedResAnswer.getId());
            res.setText(updatedResAnswer.getText());
            res.setCreated_at(new Date());

            QuestionResponseDto ques = new QuestionResponseDto();
            ques.setId(updatedResAnswer.getQuestion().getId());
            ques.setTitle(updatedResAnswer.getQuestion().getTitle());
            ques.setBody(updatedResAnswer.getQuestion().getBody());
            // ques.setTopicIds(updatedAnswer.getQuestion().getTopicIds());

            res.setQues(ques);


            UserDto usr = new UserDto();
            usr.setId(updatedResAnswer.getAnswerUser().getId());
            usr.setUserName(updatedResAnswer.getAnswerUser().getUserName());
            usr.setEmail(updatedResAnswer.getAnswerUser().getEmail());
            usr.setBio(updatedResAnswer.getAnswerUser().getBio());

            res.setUser(usr);

            return res;
                
           }
        }
        return null;
    }
    
}
