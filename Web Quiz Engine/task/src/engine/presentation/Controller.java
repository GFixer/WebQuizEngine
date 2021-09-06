package engine.presentation;

import engine.business.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;

@RestController
public class Controller {

    final
    QuizService quizService;

    final
    UserService userService;

    final
    QuizCompletionService quizCompletionService;

    private final IAuthenticationFacade authenticationFacade;

    public Controller(QuizService quizService, QuizCompletionService quizCompletionService,
                      UserService userService, IAuthenticationFacade authenticationFacade) {
        this.quizService = quizService;
        this.quizCompletionService = quizCompletionService;
        this.userService = userService;
        this.authenticationFacade = authenticationFacade;
    }

    @GetMapping("/api/quizzes/{id}")
    public Object getQuiz(@PathVariable Long id) {
        Quiz quiz = quizService.findQuizById(id);
        if (quiz == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return quiz;
    }

    @GetMapping("/api/quizzes")
    public Object getAllQuizzes(@RequestParam(required = false, defaultValue = "10") Integer page,
                                @RequestParam(defaultValue = "10") Integer pageSize,
                                @RequestParam(defaultValue = "id") String sortBy) {
        return quizService.findAll(page, pageSize, sortBy);
    }

    @GetMapping("/api/quizzes/completed")
    public Object getCompletedQuizzes(@RequestParam(required = false, defaultValue = "10") Integer page,
                                      @RequestParam(defaultValue = "10") Integer pageSize,
                                      @RequestParam(defaultValue = "id") String sortBy) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserName = "";
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            currentUserName = authentication.getName();
        }
        User currentUser = userService.findUserByEmail(currentUserName);
        return quizCompletionService.findCompleted(page, currentUser);
    }

    @PostMapping("/api/quizzes")
    public Object addQuiz(@Valid @RequestBody Quiz quiz) {
        quizService.save(quiz);
        return quiz;
    }

    @PostMapping("api/quizzes/{id}/solve")
    public Object postQuiz(@PathVariable Long id, @RequestBody Answer answer) {
        Quiz quiz = quizService.findQuizById(id);
        String currentUserName = "";
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            currentUserName = authentication.getName();
        }
        User currentUser = userService.findUserByEmail(currentUserName);
        if (answer.getAnswer().equals(quiz.getAnswer())) {
            QuizCompletion quizCompletion = new QuizCompletion(quiz.getId(),
                    LocalDateTime.now(), currentUser);
            quizCompletionService.save(quizCompletion);
            return new QuizResponse(true, "Congratulations, you're right!");
        } else {
            System.out.println(quiz);
            System.out.println(answer);
            return new QuizResponse(false, "Wrong answer! Please, try again.");
        }
    }

    @PostMapping("api/register")
    public Object registerUser(@Valid @RequestBody User user) {
        System.out.println(user.toString());
        if (user.getEmail() == null || user.getPassword() == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Object registered = userService.registerNewUserAccount(user);
        if (registered == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
            System.out.println(registered);
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }

    @DeleteMapping("/api/quizzes/{id}")
    public ResponseEntity<Void> deleteRecipe(@PathVariable long id) {
        Authentication authentication = authenticationFacade.getAuthentication();
        if (quizService.findQuizById(id) == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        if (!quizService.findQuizById(id).getUser().getEmail().equals(authentication.getName())) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        quizService.deleteQuizById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
