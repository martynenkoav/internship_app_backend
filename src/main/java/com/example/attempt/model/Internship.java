package com.example.attempt.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"})
@Entity
@Data
@Table(name = "internship")
public class Internship {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "description")
    private String description;

    @Column(name = "name")
    private String name;

    @Column(name = "url")
    private String url;

    @Column(name = "responses")
    private Long responses;

    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;


    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "internships_tags",
            joinColumns = @JoinColumn(name = "internship_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id"))
    private Set<Tag> tags = new HashSet<>();

    public Set<Tag> getTags() {
        return tags;
    }

    public void setTags(Set<Tag> tags) {
        this.tags = tags;
    }

    public Internship(String description, String name, String url, Long responses) {
        this.description = description;
        this.name = name;
        this.url = url;
        this.responses = responses;
    }

    public Internship() {
    }
}
