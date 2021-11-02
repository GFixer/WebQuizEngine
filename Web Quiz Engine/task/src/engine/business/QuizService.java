package engine.business;

import engine.persistence.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class QuizService {
    private final QuizRepository quizRepository;

    @Autowired
    public QuizService(QuizRepository quizRepository, UserService userService,
                       IAuthenticationFacade authenticationFacade) {
        this.quizRepository = quizRepository;
        this.userService = userService;
        this.authenticationFacade = authenticationFacade;
    }

    private final UserService userService;

    private final IAuthenticationFacade authenticationFacade;

    public Quiz save(Quiz quiz) {
        Authentication authentication = authenticationFacade.getAuthentication();
        quiz.setUser(userService.findUserByEmail(authentication.getName()));
        return quizRepository.save(quiz);
    }

    public Quiz findQuizById(Long id) {
        if (quizRepository.findById(id).isPresent()) {
            return quizRepository.findById(id).get();
        } else {
            return null;
        }
    }

    public Page<Quiz> findAll(Integer page, Integer pageSize, String sortBy) {
        Pageable paging = PageRequest.of(page, pageSize, Sort.by(sortBy));
        return quizRepository.findAll(paging);
    }

    public void deleteQuizById(Long id) {
        quizRepository.deleteById(id);
    }
}
