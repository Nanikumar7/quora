package com.example.demo.dtos;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AnswerResponseDto {

    private Long id;
    private String text;
    private Date created_at;
    private QuestionResponseDto ques;
    private UserDto user;
    
}
