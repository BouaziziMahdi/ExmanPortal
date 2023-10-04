package com.bank.banking.controlleurs;

import com.bank.banking.Services.impl.QuestionServiceImpl;
import com.bank.banking.Services.impl.QuizServiceImpl;
import com.bank.banking.dto.Questionsdto;
import com.bank.banking.dto.Quizdto;
import com.bank.banking.models.Questions;
import com.bank.banking.models.Quiz;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/questions")
@CrossOrigin("*")
@Tag(name = "questions", description = "the questions API")
public class QuestionsController {
    private final QuestionServiceImpl questionService;
    private final QuizServiceImpl quizService;

    @PostMapping("/add")
    public ResponseEntity<Questionsdto> addQuestion(@RequestBody @Valid Questionsdto questionsdto) {
        Questionsdto addedQuestion = questionService.addQuestion(questionsdto);
        return ResponseEntity.status(HttpStatus.CREATED).body(addedQuestion);
    }

    @PutMapping("/update")
    public ResponseEntity<Integer> updateQuestion(@RequestBody Questionsdto questionsdto) {
        return ResponseEntity.ok(this.questionService.updateQuestion(questionsdto));
    }

    @GetMapping("/quiz/{idQuiz}")
    public List<Questionsdto> getQuestionsofQuiz(@PathVariable("idQuiz") @Valid Integer id) {
        Quizdto quiz = this.quizService.getQuizById(id);
        List<Questionsdto> questions = this.questionService.getQuestionOfQuiz(quiz);
        if (questions == null) {
            return new ArrayList<>();
        } else if (questions.size() > Integer.parseInt(quiz.getNumberOfQuestion())) {
            questions = questions.subList(0, Integer.parseInt(quiz.getNumberOfQuestion()));
        }

        // Assurez-vous que questions est une liste modifiable (ArrayList)
        List<Questionsdto> mutableQuestions = new ArrayList<>(questions);

        Collections.shuffle(mutableQuestions); // Mélangez la liste modifiable
        return mutableQuestions;
    }

    @GetMapping("/quiz/all/{idQuiz}")
    public List<Questionsdto> getQuestionsofQuizAdmin(@PathVariable("idQuiz") Integer id) {
        // Créez un objet Quizdto et initialisez-le avec l'ID du quiz à récupérer
        Quizdto quizdto = new Quizdto();
        quizdto.setId(id);

        // Appelez le service pour récupérer les questions associées à ce quiz
        List<Questionsdto> questionsdtos = this.questionService.getQuestionOfQuiz(quizdto);

        return questionsdtos;
    }


    @GetMapping("/")
    public ResponseEntity<?> getAllQuestions() {
        return ResponseEntity.ok(this.questionService.getAllQuestions());
    }

    @GetMapping("/{idQuestion}")
    public ResponseEntity<Questionsdto> getQuestionById(@PathVariable("idQuestion") Integer id) {
        return ResponseEntity.ok(this.questionService.getQuestionById(id));
    }

    @DeleteMapping(value = "/quiz/{idQuestion}")
    public void deleteQuestion(@PathVariable("idQuestion") Integer id) {
        // Delete the quiz with the specified ID
        this.questionService.deleteQuestion(id);

    }

    @PostMapping("/eval-quiz")
    public ResponseEntity<?> evalQuiz(@RequestBody @Valid List<Questionsdto> questions) {
        int MarksGot = 0;
        int correctAnswers = 0;
        int attempted = 0;
        int totalQuestions = questions.size();
        for (Questionsdto question : questions) {
            // question.getQuiz().setId(1);
            Questionsdto question1 = this.questionService.getQuestion(question.getId());
            if (question1.getAnswer().equals(question.getGivenAnswer())) {
                correctAnswers++;
                double marksSingle = Double.parseDouble(question1.getQuiz().getMaxMarks()) / totalQuestions;
                MarksGot += marksSingle;
            }
            if (question.getGivenAnswer() != null) {
                attempted++;
            }
            Map<String, Object> response = Map.of(
                    "MarksGot", MarksGot,
                    "correctAnswers", correctAnswers,
                    "attempted", attempted,
                    "totalQuestions", totalQuestions
            );

        }
        return ResponseEntity.ok(Map.of(
                "MarksGot", MarksGot,
                "correctAnswers", correctAnswers,
                "attempted", attempted,
                "totalQuestions", totalQuestions
        ));
    }
}