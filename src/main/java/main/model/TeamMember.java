package main.model;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "team_members")
public class TeamMember
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "member_id")
    private int memberId;
    @Column(name = "team_id")
    @ManyToOne(cascade = CascadeType.ALL)
    private int teamId;
    private String surname;
    private String name;
    private String patronymic;
    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;
    @Column(name = "position_in_the_team")
    private String positionInTheTeam;

    public int getMemberId() {
        return memberId;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }

    public int getTeamId() {
        return teamId;
    }

    public void setTeamId(int teamId) {
        this.teamId = teamId;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }
    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(int year, int month, int day) {
        dateOfBirth = LocalDate.of(year, month, day);
    }
    public String getPositionInTheTeam() {
        return positionInTheTeam;
    }

    public void setPositionInTheTeam(String positionInTheTeam) {
        this.positionInTheTeam = positionInTheTeam;
    }

}
