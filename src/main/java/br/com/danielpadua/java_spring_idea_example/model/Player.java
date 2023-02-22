package br.com.danielpadua.java_spring_idea_example.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Player {
    private String name;
    private int number;
    private Position position;
}
