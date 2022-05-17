package com.example.attempt.model;

import lombok.Data;
import javax.persistence.*;

@Entity
@Data
@Table(name = "tag")
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "name")
    private ETag name;

    public Tag() {
    }

    public Tag(ETag name) {
        this.name = name;
    }
}