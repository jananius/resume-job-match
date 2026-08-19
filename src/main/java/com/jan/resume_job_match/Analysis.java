package com.jan.resume_job_match;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "analyses")
public class Analysis {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String candidateName;

    private String email;

    private String jobTitle;

    private Integer matchScore;

    @Column(length = 2000)
    private String matchedSkills;

    @Column(length = 2000)
    private String missingSkills;

    private LocalDateTime analyzedAt;


    public Analysis() {
    }


    public Analysis(
            String candidateName,
            String email,
            String jobTitle,
            Integer matchScore,
            String matchedSkills,
            String missingSkills,
            LocalDateTime analyzedAt) {

        this.candidateName = candidateName;
        this.email = email;
        this.jobTitle = jobTitle;
        this.matchScore = matchScore;
        this.matchedSkills = matchedSkills;
        this.missingSkills = missingSkills;
        this.analyzedAt = analyzedAt;
    }


    public Long getId() {
        return id;
    }


    public String getCandidateName() {
        return candidateName;
    }


    public void setCandidateName(String candidateName) {
        this.candidateName = candidateName;
    }


    public String getEmail() {
        return email;
    }


    public void setEmail(String email) {
        this.email = email;
    }


    public String getJobTitle() {
        return jobTitle;
    }


    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }


    public Integer getMatchScore() {
        return matchScore;
    }


    public void setMatchScore(Integer matchScore) {
        this.matchScore = matchScore;
    }


    public String getMatchedSkills() {
        return matchedSkills;
    }


    public void setMatchedSkills(String matchedSkills) {
        this.matchedSkills = matchedSkills;
    }


    public String getMissingSkills() {
        return missingSkills;
    }


    public void setMissingSkills(String missingSkills) {
        this.missingSkills = missingSkills;
    }


    public LocalDateTime getAnalyzedAt() {
        return analyzedAt;
    }


    public void setAnalyzedAt(LocalDateTime analyzedAt) {
        this.analyzedAt = analyzedAt;
    }
}