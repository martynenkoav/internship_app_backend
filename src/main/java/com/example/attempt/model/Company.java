package com.example.attempt.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "company")
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "email")
    private String email;

    @Column(name = "address")
    private String address;

    @OneToOne(fetch = FetchType.LAZY)
    private User user;
}
