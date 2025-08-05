package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dtos.QuestionDto;
import com.example.demo.dtos.QuestionResponseDto;
import com.example.demo.dtos.SearchQuestionRequestDto;
import com.example.demo.dtos.SearchQuestionResponseDto;
import com.example.demo.service.QuestionService;

@RestController
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @PostMapping("/questions")
    public QuestionResponseDto postNewQuestion(@RequestBody  QuestionDto questionDto) {
       
        
        return questionService.postNewQuestion( questionDto);
    }



    @GetMapping("/questions/search")
    public List<SearchQuestionResponseDto> searchQuestions(@RequestBody SearchQuestionRequestDto searchQuestionRequestDto){

        
        return questionService.searchQuestions(searchQuestionRequestDto);
    }
}
