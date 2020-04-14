package com.soumya.springbasic.model;

import java.util.List;

public class Question {
    private String questionId;
    private String description;
    private String answer;
    private List<String> options;


    public Question(String questionId, String description, String answer, List<String> options) {
        this.questionId = questionId;
        this.description = description;
        this.answer = answer;
        this.options = options;
    }

    public int hashcode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((questionId == null) ? 0 : hashCode());
        return result;
    }

    @Override
    public String toString() {
        return String.format("Questions [id = %s , description= %s , answer = %s , options =%s ]", this.questionId, this.description, this.answer, this.options);
    }

    @Override
    public boolean equals(Object question) {
        if (this == question)
            return true;
        if (question == null)
            return false;
        if (this.getClass() != question.getClass())
            return false;
        Question myQuestion = (Question) question;
        if (questionId == null) {
            return myQuestion.getQuestionId() == null;
        } else return this.questionId.equals(myQuestion.getQuestionId());
    }

    public String getQuestionId() {
        return questionId;
    }

    public void setQuestionId(String questionId) {
        this.questionId = questionId;
    }

    public String getDescription() {
        return description;
    }

    public String getAnswer() {
        return answer;
    }

    public List<String> getOptions() {
        return options;
    }

}
