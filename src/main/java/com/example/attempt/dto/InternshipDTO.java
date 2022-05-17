package com.example.attempt.dto;

import com.example.attempt.model.Company;
import com.example.attempt.model.ETag;
import com.example.attempt.model.Internship;
import com.example.attempt.model.Tag;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class InternshipDTO {
    private Long id;
    private String name;
    private String description;
    private Long company_id;
    private String url;
    private Long responses;

    private Set<String> tags = new HashSet<>();

    public Set<String> getTags() {
        return tags;
    }

    public void setTags(Set<String> tags) {
        this.tags = tags;
    }


    InternshipDTO(Long id, String name, String description, Long company_id, String url, Long responses, Set<String> tag) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.company_id = company_id;
        this.url = url;
        this.responses = responses;
        this.tags = tags;
    }

    public Internship toInternship() {
        Internship internship = new Internship(
                this.getDescription(),
                this.getName(),
                this.getUrl(),
                this.getResponses());
        Set<Tag> tagsCur = new HashSet<>();
        for (String tag : this.tags) {
            Tag tagCur = new Tag();
            tagCur.setId((long) ETag.valueOf(tag).ordinal() + 1);
            tagCur.setName(ETag.valueOf(tag));
            tagsCur.add(tagCur);
        }
        internship.setTags(tagsCur);
        Long company_id = this.getCompany_id();
        Company company = new Company();
        company.setId(company_id);

        internship.setCompany(company);
        return internship;
    }

    public InternshipDTO(Internship internship) {
        this.id = internship.getId();
        this.name = internship.getName();
        this.description = internship.getDescription();
        this.company_id = internship.getCompany().getId();
        this.url = internship.getUrl();
        this.responses = internship.getResponses();
        for (Tag tag : internship.getTags()) {
            this.tags.add(tag.getName().toString());
        }
    }
}
