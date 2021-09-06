package engine.persistence;

import engine.business.Quiz;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;
import java.util.Optional;

public interface QuizRepository extends PagingAndSortingRepository<Quiz, Long> {
    Optional<Quiz> findById(Long id);

    List<Quiz> findAll();

    void deleteById(Long id);
}
