package com.soumya.springbasic.controller;

import com.soumya.springbasic.model.Question;
import com.soumya.springbasic.model.Survey;
import com.soumya.springbasic.service.SurveyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
public class SurveyController {

    @Autowired
    private SurveyService surveyService;


    @GetMapping("/surveys")
    public List<Survey> retiveAllSurvey() {
        return surveyService.retrieveAllSurveys();
    }

    @GetMapping("/surveys/{surveyId}/questions")
    public List<Question> retriveQuestionsForSurvey(@PathVariable String surveyId) {
        if (surveyService.retrieveQuestions(surveyId).isPresent())
            return surveyService.retrieveQuestions(surveyId).get();
        else
            return null;
    }

    /**
     * sample request
     * {
     * "description": "Second Most Populous Country in the World",
     * "answer": "India",
     * "options": [
     * "India",
     * "Russia",
     * "United States",
     * "China"
     * ]
     * }
     */
    @PostMapping("/surveys/{surveyId}/questions")
    public ResponseEntity<?> addQuestionToSurvey(@PathVariable String surveyId, @RequestBody Question newQuestion) {
        Optional<Question> myQuestion = surveyService.addQuestion(surveyId, newQuestion);
        if (!myQuestion.isPresent()) {
            return ResponseEntity.noContent().build();
        }
        URI location = ServletUriComponentsBuilder.fromCurrentContextPath().path("/{id}").buildAndExpand(myQuestion.get().getQuestionId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @GetMapping("/surveys/{surveyId}/questions/{questionId}")
    public Optional<Question> retriveQuestionForSurvey(@PathVariable String surveyId, @PathVariable String questionId) {
        return surveyService.retrieveQuestion(surveyId, questionId);
    }

    @PostMapping("/surveys")
    public ResponseEntity<?> createSurvey(@RequestBody Survey newsurvey) {
        Survey survey = surveyService.addSurvey(newsurvey);
        if (survey == null)
            return ResponseEntity.noContent().build();
        URI location = ServletUriComponentsBuilder.fromCurrentContextPath().path("/{id}").buildAndExpand(survey.getSurveyId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @DeleteMapping("/surveys/{surveyId}")
    public ResponseEntity<?> removeSurveyByID(@PathVariable String surveyId) {
        boolean isDeleted = surveyService.removeSurvey(surveyId);
        if (isDeleted)
            return ResponseEntity.ok("Deleted");
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/surveys/{surveyId}/questions/{questionId}")
    public ResponseEntity<?> removeQuestionForSurvey(@PathVariable String surveyId, @PathVariable String questionId) {
        boolean isDeleted = surveyService.removeQuestion(surveyId, questionId);
        if (isDeleted)
            return ResponseEntity.ok("Deleted");
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/surveys/{surveyId}")
    public ResponseEntity<?> updateSurvey(@PathVariable String surveyId, @RequestBody Survey survey) {
        boolean ismodified = surveyService.modifySurvey(surveyId, survey);
        if (ismodified)
            return ResponseEntity.ok("Updated successfully");
        return ResponseEntity.notFound().build();
    }

}
