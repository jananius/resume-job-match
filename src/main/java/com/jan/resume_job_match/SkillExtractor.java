package com.jan.resume_job_match;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class SkillExtractor {

    private final List<String> knownSkills = Arrays.asList(
            "Java",
            "Python",
            "C",
            "SQL",
            "HTML",
            "CSS",
            "JavaScript",
            "Spring Boot",
            "Machine Learning",
            "Deep Learning",
            "Data Science",
            "Data Analytics",
            "Power BI",
            "Tableau",
            "Git",
            "GitHub",
            "Excel",
            "Pandas",
            "NumPy",
            "TensorFlow",
            "PyTorch",
            "Communication",
            "Problem Solving",
            "Teamwork",
            "Leadership"
    );

    public List<String> extractSkills(String resumeText) {

        List<String> detectedSkills = new ArrayList<>();

        String text = resumeText.toLowerCase();

        for (String skill : knownSkills) {

            if (text.contains(skill.toLowerCase())) {
                detectedSkills.add(skill);
            }
        }

        return detectedSkills;
    }
}