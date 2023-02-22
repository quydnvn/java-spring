package br.com.danielpadua.java_spring_idea_example.controllers;

import br.com.danielpadua.java_spring_idea_example.model.Player;
import br.com.danielpadua.java_spring_idea_example.model.Position;
import br.com.danielpadua.java_spring_idea_example.model.TeamAndSubstitute;
import br.com.danielpadua.java_spring_idea_example.repository.PlayerRepository;
import br.com.danielpadua.java_spring_idea_example.service.CoachService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

/**
 * ExampleController
 *
 * @author danielpadua
 *
 */
@RestController
@RequestMapping("/api/example")
public class ExampleController {
    @Autowired
    private CoachService coachService;
    @Autowired
    private PlayerRepository playerRepository;

    @GetMapping("/hello-world")
    public ResponseEntity<String> get() {
        return ResponseEntity.ok("Hello World!");
    }

    @GetMapping("/select")
    public Set<Player> selectPlayer() {
        return playerRepository.chooseUniquePlayersByPosition(Position.FW, 1);
    }
    @GetMapping("/maketeam")
    public Set<Player> makeTeam() {
        return coachService.chooseTeam(3,4,5);
    }
    @GetMapping("/maketeam2/{pattern}")
    public Set<Player> makeTeam2(@PathVariable String pattern) {
        String[] patterns = pattern.split("-");
        return coachService.chooseTeam(Integer.parseInt(patterns[0]), Integer.parseInt(patterns[1]), Integer.parseInt(patterns[2]));
    }

    @GetMapping("/substitude/{playerno}/{newPosition}")
    public TeamAndSubstitute substitude(@PathVariable int playerno, @PathVariable Position position) {
        return new TeamAndSubstitute();
    }
}
