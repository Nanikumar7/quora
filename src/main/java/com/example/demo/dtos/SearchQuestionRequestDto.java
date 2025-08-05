package com.example.demo.dtos;

import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;

@Getter 
@Setter
public class SearchQuestionRequestDto {

    @Column(nullable=true)
    private String text;

    @Column(nullable=true)
    private String tag;
}
