package edu.iu.c322.test1.model;

import java.util.Arrays;

public class Question {

    public Question(int id, String description, String answer, String[] choices) {
        this.id = id;
        this.description = description;
        this.answer = answer;
        this.choices = choices;
    }

    private int id;
    private String description;
    private String answer;
    private String[] choices;

    public String toLine() {
        String choicesAsString = (getChoices() == null) ? "" : String.join(",", getChoices());
        String line = String.format("%d,%s,%s,%s",
                getId(),
                getDescription().trim(),
                getAnswer().trim(),
                choicesAsString.trim());
        return line;
    }

    public static Question fromLine(String line) {
      String[] tokens = line.split(",");
      String[] choiceTokens = Arrays.copyOfRange(tokens, 3, tokens.length);
      Question q = new Question(Integer.parseInt(tokens[0]),
              tokens[1].trim(),
              tokens[2].trim(),
              choiceTokens);
      return q;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String[] getChoices() {
        return choices;
    }

    public void setChoices(String[] choices) {
        this.choices = choices;
    }
}
