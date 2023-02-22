package br.com.danielpadua.java_spring_idea_example.service;

import br.com.danielpadua.java_spring_idea_example.exception.TeamException;
import br.com.danielpadua.java_spring_idea_example.model.Player;
import br.com.danielpadua.java_spring_idea_example.model.Position;
import br.com.danielpadua.java_spring_idea_example.model.Substitute;
import br.com.danielpadua.java_spring_idea_example.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class CoachService {
    @Autowired
    private PlayerRepository playerRepository;
    HashSet<Player> currentTeam;
    List<Substitute> substituteHistory;
    public CoachService() {
        currentTeam = new HashSet<>();
        substituteHistory = new ArrayList<>();
    }

    public Set<Player> chooseTeam(int dfNum, int mfNum, int fwNum) {
        if ((dfNum + mfNum + fwNum) != 10) {
            throw new IllegalArgumentException("Sum of defenders " + dfNum + " and mid fielders " + mfNum + " and fowarder " + fwNum + " must be 10!");
        }
        currentTeam.clear();  //Xoá hết rồi chọn lại
        substituteHistory.clear();

        currentTeam.addAll(playerRepository.chooseUniquePlayersByPosition(Position.GK, 1));
        currentTeam.addAll(playerRepository.chooseUniquePlayersByPosition(Position.DF, dfNum));
        currentTeam.addAll(playerRepository.chooseUniquePlayersByPosition(Position.FW, fwNum));
        currentTeam.addAll(playerRepository.chooseUniquePlayersByPosition(Position.MF, mfNum));

        return currentTeam;
    }

    public Set<Player> getCurrentTeam() {
        return currentTeam;
    }

    public List<Substitute> subtitude(int playerno, Position position) throws TeamException{
        if (currentTeam == null) {
            throw new TeamException("Team is not formed yet");
        }

        if (substituteHistory.size() == 5) {
            throw new TeamException("Number of substitution exceeds 5");
        }
        return null;
    }

    public List<Player> availablePlayers() {
        List<Player> available = new ArrayList<Player>();
        for (Player i: playerRepository.getPlayers()) {
            if (!currentTeam.contains(i) && !substituteHistory.contains(i)) {
                available.add(i);
            }
        }
        return available;
    }

}
