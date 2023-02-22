package br.com.danielpadua.java_spring_idea_example.exception;

public class TeamException extends Exception{
    private static final long serialVersionUID = 743962959923490925L;

    public TeamException(String message) {
        super(message);
    }
}
