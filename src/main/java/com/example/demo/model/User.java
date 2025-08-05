package com.example.demo.model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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


public class User {

    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Id
    private Long id;

     @Column(nullable=false)
    private String userName;

     @Column(nullable=false)
    private String email;

    @Column(nullable=true)
    private String bio;

    @OneToMany(mappedBy="user")
    private List<Question> questions;

}
