package com.soumya.springbasic.controller;

import com.soumya.springbasic.configuration.ApplicationPropertiesConfiguration;
import com.soumya.springbasic.model.Question;
import com.soumya.springbasic.model.Survey;
import com.soumya.springbasic.service.SurveyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
public class SurveyController {

    @Autowired
    private SurveyService surveyService;
    @Value("${crud.updateMessage}")
    private String updateMessage;
    @Value("${crud.deleteMessage}")
    private String deleteMessage;

    @Autowired
    private ApplicationPropertiesConfiguration appConfig;
    //private ObjectMapper objectMapper = registerJdkModuleAndGetMapper();

    @GetMapping("/surveys")
    public List<Survey> retiveAllSurvey() {
        return surveyService.retrieveAllSurveys();
    }

    @GetMapping("/surveys/{surveyId}/questions")
    public List<Question> retriveQuestionsForSurvey(@PathVariable String surveyId) {
        Optional<List<Question>> questions = surveyService.retrieveQuestions(surveyId);
        return questions.orElse(null);
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
        if (myQuestion.isEmpty()) {
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
            return ResponseEntity.ok(deleteMessage);
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/surveys/{surveyId}/questions/{questionId}")
    public ResponseEntity<?> removeQuestionForSurvey(@PathVariable String surveyId, @PathVariable String questionId) {
        boolean isDeleted = surveyService.removeQuestion(surveyId, questionId);
        if (isDeleted)
            return ResponseEntity.ok(deleteMessage);
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/surveys/{surveyId}")
    public ResponseEntity<?> updateSurvey(@PathVariable String surveyId, @RequestBody Survey survey) {
        boolean ismodified = surveyService.modifySurvey(surveyId, survey);
        if (ismodified)
            return ResponseEntity.ok(updateMessage);
        return ResponseEntity.notFound().build();
    }

    @RequestMapping(value = "/myconfig", method = RequestMethod.GET)
    public Map getApplicationProperties() {
        Map map = new HashMap();
        map.put("applicationName", appConfig.getName());
        map.put("applicationType", appConfig.getType());
        map.put("applicationProgramingLanguage", appConfig.getProgramminglanguage());
        return map;
    }

    /*@Bean
    public ObjectMapper registerJdkModuleAndGetMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        Jdk8Module module = new Jdk8Module();
        module.configureAbsentsAsNulls(true);
        objectMapper.registerModule(module);
        return objectMapper;
    }

    public JsonNode retriveQuestionsForSurvey1(@PathVariable String surveyId) throws JsonProcessingException {
        ObjectMapper mapper = registerJdkModuleAndGetMapper();
        String jsonString = mapper.writeValueAsString(surveyService.retrieveQuestions(surveyId));
        return mapper.readTree(jsonString);

    }*/
}
