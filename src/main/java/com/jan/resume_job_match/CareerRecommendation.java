package com.jan.resume_job_match;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CareerRecommendation {

    public List<String> generateRecommendations(List<String> missingSkills) {

        List<String> recommendations = new ArrayList<>();

        for (String skill : missingSkills) {

            switch (skill.toLowerCase()) {

                case "spring boot":
                    recommendations.add(
                            "Learn Spring Boot and build REST API projects."
                    );
                    break;

                case "machine learning":
                    recommendations.add(
                            "Strengthen Machine Learning fundamentals and build practical ML projects."
                    );
                    break;

                case "python":
                    recommendations.add(
                            "Practice Python programming and solve data-oriented problems."
                    );
                    break;

                case "sql":
                    recommendations.add(
                            "Practice SQL queries, joins, subqueries and database design."
                    );
                    break;

                case "power bi":
                    recommendations.add(
                            "Build interactive Power BI dashboards using real datasets."
                    );
                    break;

                case "tableau":
                    recommendations.add(
                            "Practice Tableau and create data visualization dashboards."
                    );
                    break;

                case "git":
                    recommendations.add(
                            "Practice Git commands and collaborative version control."
                    );
                    break;

                case "github":
                    recommendations.add(
                            "Maintain professional GitHub repositories with proper README files."
                    );
                    break;

                case "data science":
                    recommendations.add(
                            "Build end-to-end Data Science projects using real-world datasets."
                    );
                    break;

                case "data analytics":
                    recommendations.add(
                            "Practice data cleaning, visualization and analytical storytelling."
                    );
                    break;

                case "html":
                    recommendations.add(
                            "Strengthen HTML fundamentals and build responsive web pages."
                    );
                    break;

                case "css":
                    recommendations.add(
                            "Practice CSS layouts and responsive web design."
                    );
                    break;

                case "javascript":
                    recommendations.add(
                            "Learn JavaScript fundamentals and browser-side development."
                    );
                    break;

                default:
                    recommendations.add(
                            "Improve your knowledge of " + skill +
                            " through practice and a practical project."
                    );
            }
        }

        if (recommendations.isEmpty()) {

            recommendations.add(
                    "Great job! Your resume covers all detected job skills."
            );
        }

        return recommendations;
    }
}