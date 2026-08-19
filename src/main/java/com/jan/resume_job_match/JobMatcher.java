package com.jan.resume_job_match;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class JobMatcher {

    private final SkillExtractor skillExtractor;

    public JobMatcher(SkillExtractor skillExtractor) {
        this.skillExtractor = skillExtractor;
    }

    public MatchResult match(String resumeText, String jobDescription) {

        List<String> resumeSkills =
                skillExtractor.extractSkills(resumeText);

        List<String> jobSkills =
                skillExtractor.extractSkills(jobDescription);

        List<String> matchedSkills = new ArrayList<>();
        List<String> missingSkills = new ArrayList<>();

        for (String jobSkill : jobSkills) {

            boolean matched = resumeSkills.stream()
                    .anyMatch(resumeSkill ->
                            resumeSkill.equalsIgnoreCase(jobSkill));

            if (matched) {
                matchedSkills.add(jobSkill);
            } else {
                missingSkills.add(jobSkill);
            }
        }

        int score = calculateScore(
                jobSkills,
                matchedSkills
        );

        return new MatchResult(
                score,
                matchedSkills,
                missingSkills
        );
    }

    private int calculateScore(
            List<String> jobSkills,
            List<String> matchedSkills) {

        if (jobSkills.isEmpty()) {
            return 0;
        }

        double score =
                ((double) matchedSkills.size()
                        / jobSkills.size()) * 100;

        return (int) Math.round(score);
    }

    public static class MatchResult {

        private final int score;
        private final List<String> matchedSkills;
        private final List<String> missingSkills;

        public MatchResult(
                int score,
                List<String> matchedSkills,
                List<String> missingSkills) {

            this.score = score;
            this.matchedSkills = matchedSkills;
            this.missingSkills = missingSkills;
        }

        public int getScore() {
            return score;
        }

        public List<String> getMatchedSkills() {
            return matchedSkills;
        }

        public List<String> getMissingSkills() {
            return missingSkills;
        }
    }
}