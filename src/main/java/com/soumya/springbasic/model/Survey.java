package com.soumya.springbasic.model;

import java.util.List;
import java.util.Optional;

public class Survey {
    private String surveyId;
    private Optional<String> title;
    private Optional<String> description;
    private Optional<List<Question>> questions;

    public Survey(String surveyId, Optional<String> title, Optional<String> description, Optional<List<Question>> questions) {
        this.surveyId = surveyId;
        this.title = title;
        this.description = description;
        this.questions = questions;
    }

    public String getSurveyId() {
        return surveyId;
    }

    public void setSurveyId(String surveyId) {
        this.surveyId = surveyId;
    }

    public Optional<String> getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = Optional.ofNullable(title);
    }

    public Optional<String> getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = Optional.ofNullable(description);
    }

    public Optional<List<Question>> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = Optional.ofNullable(questions);
    }

    @Override
    public String toString() {
        return String.format("Survey [id = %s , title= %s , description = %s , questions =%s ]", this.surveyId, this.title, this.description, this.questions);
    }
}
