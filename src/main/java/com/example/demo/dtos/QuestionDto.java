package com.example.demo.dtos;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QuestionDto {

    private Long userId;
    private String title;
    private String body;
    private List<Long> topicIds;
}
