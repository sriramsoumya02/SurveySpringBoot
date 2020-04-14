package com.soumya.springbasic.model;

import java.util.List;

public class Survey {
    private String surveyId;
    private String title;
    private String description;
    private List<Question> questions;

    public Survey(String surveyId, String title, String description, List<Question> questions) {
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    @Override
    public String toString() {
        return String.format("Survey [id = %s , title= %s , description = %s , questions =%s ]", this.surveyId, this.title, this.description, this.questions);
    }
}
