package engine.business;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.util.ArrayList;
import java.util.List;

@Entity
public class User {

    @Id
    @GeneratedValue
    private long id;

    @Column
    @Pattern(regexp = "\\w+@\\w+\\.\\w+")
    private String email;

    @NotEmpty
    @Column
    @Length(min = 5)
    private String password;

    private boolean isActive;
    private String roles;

    @OneToMany(mappedBy = "user")
    private List<Quiz> quizzes = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<QuizCompletion> quizCompletions = new ArrayList<>();


    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public User() {
    }

    @JsonIgnore
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", isActive=" + isActive +
                ", roles='" + roles + '\'' +
                '}';
    }
}
