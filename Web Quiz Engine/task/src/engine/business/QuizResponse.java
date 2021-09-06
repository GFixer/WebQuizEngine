package engine.business;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize
public class QuizResponse {
    private boolean success;
    private String feedback;

    public QuizResponse(boolean success, String feedback) {
        this.success = success;
        this.feedback = feedback;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }
}
