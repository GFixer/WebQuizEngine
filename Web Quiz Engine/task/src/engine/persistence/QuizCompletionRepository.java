package engine.persistence;

import engine.business.QuizCompletion;
import engine.business.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface QuizCompletionRepository extends PagingAndSortingRepository<QuizCompletion, Long> {

    Page<QuizCompletion> findAllByUser(User currentUser, Pageable pageable);
}
