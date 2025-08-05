package com.example.demo.dtos;

import java.util.Date;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SearchQuestionResponseDto {
    private Long id;

    private String title;

    private String body;

    private List<TopicDto> topicDto;

    

    private UserDto user;
    
    private Date created_at;
}
