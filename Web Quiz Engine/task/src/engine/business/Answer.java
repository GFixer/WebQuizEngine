package engine.business;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.util.List;

@JsonSerialize
public class Answer {
    private List<Integer> answer;

    public Answer(List<Integer> answer) {
        this.answer = answer;
    }

    public Answer() {
    }

    public List<Integer> getAnswer() {
        return answer;
    }

    public void setAnswer(List<Integer> answer) {
        this.answer = answer;
    }

}
