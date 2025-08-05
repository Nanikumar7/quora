package com.example.demo.model;

import java.util.Date;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Question {

    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Id
    private Long id;

    @Column(nullable=false)
    private String title;

    @Column(nullable=false)
    private String body;


    // @OneToMany(mappedBy="question")
    // private List<Topic> topicIds;


    @ManyToMany
    @JoinTable(
        name="question_topics",
        joinColumns=@JoinColumn(name="question_id"),
        inverseJoinColumns=@JoinColumn(name="topic_id")
    )
    private List<Topic> topicIds;



    @Temporal(value = TemporalType.TIMESTAMP)
    private Date created_at;
    
   @ManyToOne
   private User user;
    
}
