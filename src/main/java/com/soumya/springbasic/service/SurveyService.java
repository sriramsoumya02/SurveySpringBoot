package com.soumya.springbasic.service;

import com.soumya.springbasic.model.Question;
import com.soumya.springbasic.model.Survey;
import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Component
public class SurveyService {
    static List<Survey> surveys = new ArrayList<>();

    static {
        Question question1 = new Question("Question1",
                "Largest Country in the World", "Russia", Arrays.asList(
                "India", "Russia", "United States", "China"));
        Question question2 = new Question("Question2",
                "Most Populus Country in the World", "China", Arrays.asList(
                "India", "Russia", "United States", "China"));
        Question question3 = new Question("Question3",
                "Highest GDP in the World", "United States", Arrays.asList(
                "India", "Russia", "United States", "China"));
        Question question4 = new Question("Question4",
                "Second largest english speaking country", "India", Arrays
                .asList("India", "Russia", "United States", "China"));

        List<Question> questions = new ArrayList<>(Arrays.asList(question1,
                question2, question3, question4));

        Survey survey = new Survey("Survey1", "My Favorite Survey",
                "Description of the Survey", questions);

        surveys.add(survey);
    }

    public List<Survey> retrieveAllSurveys() {
        return surveys;
    }

    public Optional<Survey> retrieveSurvey(String surveyId) {
        for (Survey survey : surveys) {
            if (survey.getSurveyId().equals(surveyId))
                return Optional.of(survey);
        }
        return Optional.empty();
    }

    public Optional<List<Question>> retrieveQuestions(String surveyId) {
        for (Survey survey : surveys) {
            if (survey.getSurveyId().equals(surveyId))
                return Optional.of(survey.getQuestions());
        }
        return Optional.empty();
    }

    public Optional<Question> retrieveQuestion(String surveyId, String questionId) {
        Optional<List<Question>> questionList = retrieveQuestions(surveyId);
        if (questionList.isPresent()) {
            for (Question question : questionList.get()) {
                if (question.getQuestionId().equals(questionId))
                    return Optional.of(question);
            }
        }
        return Optional.empty();
    }

    private SecureRandom random = new SecureRandom();

    public Optional<Question> addQuestion(String surveyId, Question question) {
        Optional<Survey> survey = retrieveSurvey(surveyId);
        if (survey.isEmpty())
            return Optional.empty();
        String randomId = new BigInteger(132, random).toString(32);
        question.setQuestionId(randomId);
        survey.get().getQuestions().add(question);
        return Optional.of(question);
    }

    public Survey addSurvey(Survey survey) {
        surveys.add(survey);
        return survey;
    }

    public boolean removeSurvey(String surveyId) {
        return surveys.removeIf(s -> s.getSurveyId().equals(surveyId));
    }

    public boolean removeQuestion(String surveyId, String questionId) {
        Optional<List<Question>> questionList = retrieveQuestions(surveyId);
        return questionList.map(questions -> questions.removeIf(q -> q.getQuestionId().equals(questionId))).orElse(false);
    }

    public boolean modifySurvey(String surveyId, Survey newSurvey) {
        Optional<Survey> survey = retrieveSurvey(surveyId);
        if (survey.isPresent()) {
            Survey mySurvey = survey.get();
            mySurvey.setDescription(newSurvey.getDescription());
            mySurvey.setTitle(newSurvey.getDescription());
            return true;
        }
        return false;
    }

}
