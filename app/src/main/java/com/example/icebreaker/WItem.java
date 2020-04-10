package com.example.icebreaker;

public class WItem {
    private boolean done;
    private String question;

    public WItem(boolean done, String question) {
        this.done = done;
        this.question = question;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }
}
