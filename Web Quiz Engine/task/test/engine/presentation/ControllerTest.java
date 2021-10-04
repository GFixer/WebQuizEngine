package engine.presentation;

import com.fasterxml.jackson.databind.ObjectMapper;
import engine.business.Quiz;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
@AutoConfigureMockMvc
class ControllerTest {
    @Autowired
    private MockMvc mockMvc;
    static Quiz testQuiz;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    public void setUp() {
        List<Integer> testAnswer = new ArrayList<>();
        testAnswer.add(1);
        String[] testQuestions = new String[2];
        testQuestions[0] = "Question 1";
        testQuestions[1] = "Question 2";
        testQuiz = new Quiz("name", "f", testQuestions, testAnswer);
        testQuiz.setId(0L);
    }

    @Test
    void getQuiz() {

    }

    @Test
    @WithMockUser(username = "user1", password = "pwd", roles = "USER")
    void getAllQuizzes() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/quizzes").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void getCompletedQuizzes() {
    }

    @Test
    @WithMockUser(username = "user1", password = "pwd", roles = "USER")
    void addQuiz() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/api/quizzes")
                .content(objectMapper.writeValueAsString(testQuiz))
                .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
    }

    @Test
    void postQuiz() {
    }

    @Test
    void registerUser() {
    }

    @Test
    void deleteRecipe() {
    }
}