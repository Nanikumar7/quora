package com.example.demo.dtos;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QuestionResponseDto {

    private Long id;
    private String title;
    private String body;
    private List<Long> topicIds;
    


}
