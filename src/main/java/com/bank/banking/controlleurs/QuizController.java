package com.bank.banking.controlleurs;

import com.bank.banking.Services.impl.QuizServiceImpl;
import com.bank.banking.dto.Categorydto;
import com.bank.banking.dto.Quizdto;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@CrossOrigin("*")
@RequestMapping("/quiz")
@Tag(name = "quiz", description = "the quiz API")
public class QuizController {
    private final QuizServiceImpl quizService;

    @PostMapping("/")
    public ResponseEntity<Quizdto> addQuiz(@RequestBody Quizdto quizdto) {
        return ResponseEntity.ok(quizService.addQuiz(quizdto));
    }

    @PutMapping("/update")
    public ResponseEntity<Integer> updateQuiz(@RequestBody Quizdto quizDto) {
        return ResponseEntity.ok(quizService.updateQuiz(quizDto));
    }

    @GetMapping("/")
    public ResponseEntity<?> getAllQuiz() {
        return ResponseEntity.ok(quizService.getAllQuiz());
    }

    @GetMapping("/{idQuiz}")
    public ResponseEntity<Quizdto> getQuizById(@PathVariable("idQuiz") Integer id) {
        return ResponseEntity.ok(quizService.getQuizById(id));
    }

    @DeleteMapping("/{idQuiz}")
    public void deleteQuiz(@PathVariable("idQuiz") Integer id) {
        quizService.deleteQuiz(id);
    }

    @GetMapping("/categories/{categoryId}")
    public List<Quizdto> getQuizOfCategory(@PathVariable("categoryId") Integer id) {
        Categorydto categorydto = new Categorydto();
        categorydto.setId(id);
        return this.quizService.getQuizOfCategory(categorydto);
    }

    @GetMapping("/active")
    public List<Quizdto> getActiveQuiz() {
        return this.quizService.getActiveQuiz();
    }

    @GetMapping("/categories/{categoryId}/active")
    public List<Quizdto> getQuizOfCategoryAndActive(@PathVariable("categoryId") Integer id) {
        Categorydto categorydto = new Categorydto();
        categorydto.setId(id);
        return this.quizService.getQuizOfCategoryAndActive(categorydto);
    }
}

