package engine.business;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class QuizCompletion {
    @GeneratedValue
    @Column
    @Id
    private long qid;

    @Column
    private Long id;

    @Column
    private LocalDateTime completedAt;

    @ManyToOne
    @JoinColumn(name = "userID")
    private User user;


    public QuizCompletion(Long id, LocalDateTime completedAt, User user) {
        this.id = id;
        this.completedAt = completedAt;
        this.user = user;
    }

    public QuizCompletion() {
    }

    @JsonIgnore
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @JsonIgnore
    public long getQid() {
        return qid;
    }

    public void setQid(long qid) {
        this.qid = qid;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getCompletedAt() {
        return completedAt;
    }

    public void setCompletedAt(LocalDateTime completedAt) {
        this.completedAt = completedAt;
    }
}
