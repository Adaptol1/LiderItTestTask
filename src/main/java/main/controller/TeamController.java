package main.controller;

import main.model.Team;
import main.model.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class TeamController {
    @Autowired
    private TeamRepository teamRepository;

    @GetMapping("/teams/")
    public List<Team> list()
    {
        Iterable<Team> teamIterable = teamRepository.findAll();
        ArrayList<Team> teams = new ArrayList<>();
        for(Team team : teamIterable) {
            teams.add(team);
        }
        return teams;
    }
    @GetMapping("/teams/filter/sport")
    public List<Team> sportList(@RequestParam String sport)
    {
        Iterable<Team> teamIterable = teamRepository.findAll();
        ArrayList<Team> teams = new ArrayList<>();
        for(Team team : teamIterable) {
            if(team.getSport().equals(sport))
            {
                teams.add(team);
            }
        }
        return teams;
    }
    @GetMapping("/teams/filter/date")
    public List<Team> foundingDateList(@RequestParam LocalDate fromDate,
                                       @RequestParam LocalDate toDate)
    {
        Iterable<Team> teamIterable = teamRepository.findAll();
        ArrayList<Team> teams = new ArrayList<>();
        for(Team team : teamIterable) {
            if(team.getFoundingDate().compareTo(fromDate) >= 0 &&
                    team.getFoundingDate().compareTo(toDate) <= 0)
            {
                teams.add(team);
            }
        }
        return teams;
    }
    @PostMapping("/teams/")
    public int add(Team team)
    {
        Team newTeam = teamRepository.save(team);
        return newTeam.getTeamId();
    }
    @PutMapping("/teams/{id}")
    public ResponseEntity update(@PathVariable int id,
                                 @RequestParam String teamName,
                                 @RequestParam String sport,
                                 @RequestParam LocalDate foundingDate)
    {
        Optional <Team> optionalTeam = teamRepository.findById(id);
        optionalTeam.get().setTeamName(teamName);
        optionalTeam.get().setSport(sport);
        optionalTeam.get().setFoundingDate(foundingDate.getYear(),
                foundingDate.getMonthValue(), foundingDate.getDayOfYear());
        teamRepository.save(optionalTeam.get());
        return new ResponseEntity(HttpStatus.OK);
    }
    @DeleteMapping("/teams/{id}")
    public ResponseEntity delete(@PathVariable int id)
    {
        Optional <Team> optionalTeam = teamRepository.findById(id);
        if(optionalTeam.get() == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        teamRepository.delete(optionalTeam.get());
        return new ResponseEntity(optionalTeam.get(), HttpStatus.OK);
    }
}
