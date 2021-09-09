package engine.business;

import engine.persistence.QuizCompletionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class QuizCompletionService {
    private final QuizCompletionRepository quizCompletionRepository;

    @Autowired
    public QuizCompletionService(QuizCompletionRepository quizCompletionRepository, UserService userService,
                                 IAuthenticationFacade authenticationFacade) {
        this.quizCompletionRepository = quizCompletionRepository;
        this.userService = userService;
        this.authenticationFacade = authenticationFacade;
    }

    final
    UserService userService;

    private final IAuthenticationFacade authenticationFacade;

    public Page<QuizCompletion> findCompleted(Integer page, User currentUser) {
        Pageable paging = PageRequest.of(page, 10, Sort.by("completedAt").descending());
        return quizCompletionRepository.findAllByUser(currentUser, paging);
    }

    public QuizCompletion save(QuizCompletion quizCompletion) {
        Authentication authentication = authenticationFacade.getAuthentication();
        quizCompletion.setUser(userService.findUserByEmail(authentication.getName()));
        return quizCompletionRepository.save(quizCompletion);
    }
}
