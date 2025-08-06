package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dtos.AnswerDto;
import com.example.demo.dtos.AnswerResponseDto;
import com.example.demo.dtos.UpdataeAnswerReqDto;
import com.example.demo.service.AnswerService;
import com.example.demo.service.QuestionService;
import com.example.demo.service.TopicService;
import com.example.demo.service.UserService;


@RestController
public class AnswerController {

    @Autowired
    private QuestionService questionService;

    @Autowired
    private UserService userService;

    @Autowired
    private TopicService topicService;

    @Autowired
    private AnswerService answerService;



    @PostMapping("/questions/{questionId}/answers")
    public AnswerResponseDto postAnswerToQuestion(@PathVariable Long questionId,@RequestBody AnswerDto answerDto ){


        return answerService.postAnswerToQuestion(questionId,answerDto);

    }


    @PutMapping("/answers/{answerId}")
    public AnswerResponseDto updateAnswer(@PathVariable Long answerId,@RequestBody UpdataeAnswerReqDto updataeAnswerReqDto){
        return  answerService.updateAnswer(answerId,updataeAnswerReqDto.getText());
    }
}
