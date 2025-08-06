package com.example.demo.model;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Answer {


    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Id
    private Long id;

    private String text;

    @Temporal(value=TemporalType.TIMESTAMP)
    private Date created_at;


    
    @ManyToOne
    @JoinColumn(name="question_id")
    private Question question;


    @ManyToOne
    @JoinColumn(name="user_id")
    private User answerUser;


}
