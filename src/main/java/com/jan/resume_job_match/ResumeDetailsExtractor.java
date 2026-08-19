package com.jan.resume_job_match;

import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class ResumeDetailsExtractor {

    public ResumeDetails extractDetails(String text) {

        String name = extractName(text);
        String email = extractEmail(text);
        String phone = extractPhone(text);
        String linkedin = extractLinkedIn(text);
        String github = extractGitHub(text);
        String cgpa = extractCGPA(text);
        String degree = extractDegree(text);

        return new ResumeDetails(
                name,
                email,
                phone,
                linkedin,
                github,
                cgpa,
                degree
        );
    }

    private String extractName(String text) {

        String[] lines = text.split("\\R");

        for (String line : lines) {

            line = line.trim();

            if (line.isEmpty()) {
                continue;
            }

            if (line.contains("@")) {
                continue;
            }

            if (line.toLowerCase().contains("linkedin")) {
                continue;
            }

            if (line.toLowerCase().contains("github")) {
                continue;
            }

            if (line.matches(".*\\d.*")) {
                continue;
            }

            if (line.length() <= 50) {
                return line;
            }
        }

        return "Not detected";
    }

    private String extractEmail(String text) {

        Pattern pattern = Pattern.compile(
                "[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}"
        );

        Matcher matcher = pattern.matcher(text);

        if (matcher.find()) {
            return matcher.group();
        }

        return "Not detected";
    }

    private String extractPhone(String text) {

        Pattern pattern = Pattern.compile(
                "(?:\\+91[-\\s]?)?[6-9]\\d{9}"
        );

        Matcher matcher = pattern.matcher(text);

        if (matcher.find()) {
            return matcher.group();
        }

        return "Not detected";
    }

    private String extractLinkedIn(String text) {

        Pattern pattern = Pattern.compile(
                "https?://(?:www\\.)?linkedin\\.com/[^\\s]+",
                Pattern.CASE_INSENSITIVE
        );

        Matcher matcher = pattern.matcher(text);

        if (matcher.find()) {
            return matcher.group();
        }

        return "Not detected";
    }

    private String extractGitHub(String text) {

        Pattern pattern = Pattern.compile(
                "https?://(?:www\\.)?github\\.com/[^\\s]+",
                Pattern.CASE_INSENSITIVE
        );

        Matcher matcher = pattern.matcher(text);

        if (matcher.find()) {
            return matcher.group();
        }

        return "Not detected";
    }

    private String extractCGPA(String text) {

        Pattern pattern = Pattern.compile(
                "(?i)(?:CGPA|GPA)\\s*[:\\-]?\\s*(\\d+(?:\\.\\d+)?)"
        );

        Matcher matcher = pattern.matcher(text);

        if (matcher.find()) {
            return matcher.group(1);
        }

        return "Not detected";
    }

    private String extractDegree(String text) {

        Pattern pattern = Pattern.compile(
                "(?i)(B\\.Tech[^\\n]*|B\\.E[^\\n]*|Bachelor[^\\n]*)"
        );

        Matcher matcher = pattern.matcher(text);

        if (matcher.find()) {
            return matcher.group(1).trim();
        }

        return "Not detected";
    }

    public static class ResumeDetails {

        private final String name;
        private final String email;
        private final String phone;
        private final String linkedin;
        private final String github;
        private final String cgpa;
        private final String degree;

        public ResumeDetails(
                String name,
                String email,
                String phone,
                String linkedin,
                String github,
                String cgpa,
                String degree) {

            this.name = name;
            this.email = email;
            this.phone = phone;
            this.linkedin = linkedin;
            this.github = github;
            this.cgpa = cgpa;
            this.degree = degree;
        }

        public String getName() {
            return name;
        }

        public String getEmail() {
            return email;
        }

        public String getPhone() {
            return phone;
        }

        public String getLinkedin() {
            return linkedin;
        }

        public String getGithub() {
            return github;
        }

        public String getCgpa() {
            return cgpa;
        }

        public String getDegree() {
            return degree;
        }
    }
}