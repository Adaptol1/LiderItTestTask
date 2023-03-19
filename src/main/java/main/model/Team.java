package main.model;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "teams")
public class Team
{
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int teamId;
    @Column(name = "team_name")
    private String teamName;
    private String sport;
    @Column(name = "founding_date")
    private LocalDate foundingDate;

    public int getTeamId() {
        return teamId;
    }

    public void setTeamId(int teamId) {
        this.teamId = teamId;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String getSport() {
        return sport;
    }

    public void setSport(String sport) {
        this.sport = sport;
    }

    public LocalDate getFoundingDate() {
        return foundingDate;
    }

    public void setFoundingDate(int year, int month, int day) {
        foundingDate = LocalDate.of(year, month, day);
    }

}
