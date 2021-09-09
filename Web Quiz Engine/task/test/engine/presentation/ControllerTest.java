package engine.presentation;

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

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
@AutoConfigureMockMvc
class ControllerTest {
    @Autowired
    private MockMvc mockMvc;

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
    void addQuiz() {
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