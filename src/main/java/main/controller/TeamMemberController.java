package main.controller;

import main.model.Team;
import main.model.TeamMember;
import main.model.TeamMembersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class TeamMemberController {
    @Autowired
    private TeamMembersRepository teamMembersRepository;

    @GetMapping("/teammembers/")
    public List<TeamMember> list()
    {
        Iterable<TeamMember> teamIterable = teamMembersRepository.findAll();
        ArrayList<TeamMember> teamMembers = new ArrayList<>();
        for(TeamMember teamMember : teamIterable) {
            teamMembers.add(teamMember);
        }
        return teamMembers;
    }
    @PostMapping("/teammembers/")
    public int add(TeamMember teamMember)
    {
        TeamMember newTeamMember = teamMembersRepository.save(teamMember);
        return teamMember.getMemberId();
    }
    @GetMapping("/teammembers/{id}")
    public ResponseEntity get(@PathVariable int id)
    {
        Optional<TeamMember> optionalTeamMember = teamMembersRepository.findById(id);
        if(!optionalTeamMember.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return new ResponseEntity(optionalTeamMember.get(), HttpStatus.OK);
    }
    @DeleteMapping("/teammembers/{id}")
    public ResponseEntity delete(@PathVariable int id)
    {
        Optional <TeamMember> optionalTeamMember = teamMembersRepository.findById(id);
        if(optionalTeamMember.get() == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        teamMembersRepository.delete(optionalTeamMember.get());
        return new ResponseEntity(optionalTeamMember.get(), HttpStatus.OK);
    }
    @DeleteMapping("/teammembers/")
    public ResponseEntity deleteAll ()
    {
        if(teamMembersRepository.count() == 0) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        teamMembersRepository.deleteAll();
        return new ResponseEntity(HttpStatus.OK);
    }
    @PutMapping("/teammembers/{id}")
    public ResponseEntity update (@PathVariable int memberId,
                                  @RequestParam int teamId,
                                  @RequestParam String surname,
                                  @RequestParam String name,
                                  @RequestParam String patronymic,
                                  @RequestParam LocalDate dateOfBirth,
                                  @RequestParam String positionInTheTeam)
    {
        Optional <TeamMember> optionalTeamMember = teamMembersRepository.findById(memberId);
        optionalTeamMember.get().setTeamId(teamId);
        optionalTeamMember.get().setSurname(surname);
        optionalTeamMember.get().setName(name);
        optionalTeamMember.get().setPatronymic(patronymic);
        optionalTeamMember.get().setDateOfBirth(dateOfBirth.getYear(), dateOfBirth.getMonthValue(), dateOfBirth.getDayOfYear());
        optionalTeamMember.get().setPositionInTheTeam(positionInTheTeam);
        teamMembersRepository.save(optionalTeamMember.get());
        return new ResponseEntity(HttpStatus.OK);
    }
}
