package com.jan.resume_job_match;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@Controller
public class ResumeController {

    private final ResumeTextExtractor resumeTextExtractor;

    private final SkillExtractor skillExtractor;

    private final JobMatcher jobMatcher;

    private final CareerRecommendation careerRecommendation;

    private final ResumeDetailsExtractor resumeDetailsExtractor;

    private final AnalysisRepository analysisRepository;


    public ResumeController(
            ResumeTextExtractor resumeTextExtractor,
            SkillExtractor skillExtractor,
            JobMatcher jobMatcher,
            CareerRecommendation careerRecommendation,
            ResumeDetailsExtractor resumeDetailsExtractor,
            AnalysisRepository analysisRepository) {

        this.resumeTextExtractor = resumeTextExtractor;
        this.skillExtractor = skillExtractor;
        this.jobMatcher = jobMatcher;
        this.careerRecommendation = careerRecommendation;
        this.resumeDetailsExtractor = resumeDetailsExtractor;
        this.analysisRepository = analysisRepository;
    }


    @PostMapping("/upload")
    public String uploadResume(
            @RequestParam(value = "resume", required = false)
            MultipartFile file,

            @RequestParam(value = "jobDescription", required = false)
            String jobDescription,

            Model model) {

        try {

            if (file == null || file.isEmpty()) {

                model.addAttribute(
                        "error",
                        "Please select a resume PDF."
                );

                return "upload-error";
            }


            if (jobDescription == null ||
                    jobDescription.trim().isEmpty()) {

                model.addAttribute(
                        "error",
                        "Please enter a job description."
                );

                return "upload-error";
            }


            String resumeText =
                    resumeTextExtractor.extractText(file);


            JobMatcher.MatchResult result =
                    jobMatcher.match(
                            resumeText,
                            jobDescription
                    );


            List<String> recommendations =
                    careerRecommendation.generateRecommendations(
                            result.getMissingSkills()
                    );


            ResumeDetailsExtractor.ResumeDetails details =
                    resumeDetailsExtractor.extractDetails(
                            resumeText
                    );


            String jobTitle =
                    extractJobTitle(jobDescription);


            String matchedSkills =
                    String.join(
                            ", ",
                            result.getMatchedSkills()
                    );


            String missingSkills =
                    String.join(
                            ", ",
                            result.getMissingSkills()
                    );


            Analysis analysis = new Analysis(

                    details.getName(),

                    details.getEmail(),

                    jobTitle,

                    result.getScore(),

                    matchedSkills,

                    missingSkills,

                    LocalDateTime.now()
            );


            analysisRepository.save(analysis);


            model.addAttribute(
                    "score",
                    result.getScore()
            );


            model.addAttribute(
                    "matchedSkills",
                    result.getMatchedSkills()
            );


            model.addAttribute(
                    "missingSkills",
                    result.getMissingSkills()
            );


            model.addAttribute(
                    "recommendations",
                    recommendations
            );


            model.addAttribute(
                    "name",
                    details.getName()
            );


            model.addAttribute(
                    "email",
                    details.getEmail()
            );


            model.addAttribute(
                    "phone",
                    details.getPhone()
            );


            model.addAttribute(
                    "linkedin",
                    details.getLinkedin()
            );


            model.addAttribute(
                    "github",
                    details.getGithub()
            );


            model.addAttribute(
                    "cgpa",
                    details.getCgpa()
            );


            model.addAttribute(
                    "degree",
                    details.getDegree()
            );


            return "upload-success";


        } catch (IOException e) {

            e.printStackTrace();

            model.addAttribute(
                    "error",
                    "Unable to process the resume PDF."
            );

            return "upload-error";
        }
    }


    private String extractJobTitle(String jobDescription) {

        String[] lines =
                jobDescription.split("\\R");

        for (String line : lines) {

            line = line.trim();

            if (!line.isEmpty()) {

                return line;
            }
        }

        return "Unknown Job";
    }
}