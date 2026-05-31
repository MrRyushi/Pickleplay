package com.example.pickleplay.openplay;

import com.example.pickleplay.SkillLevel;
import com.example.pickleplay.Status;
import com.example.pickleplay.court.Court;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
public class OpenPlay {
    @Id
    @GeneratedValue
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "court_id")
    private Court court;
    private Integer host_id;
    private LocalDateTime dateTime;
    private int duration;
    private int maxPlayers;
    private SkillLevel skillLevel;
    private int pricePerPlayer;

    public OpenPlay(Integer id, Court court, Integer host_id, LocalDateTime dateTime, int duration, int maxPlayers, SkillLevel skillLevel, int pricePerPlayer) {
        this.id = id;
        this.court = court;
        this.host_id = host_id;
        this.dateTime = dateTime;
        this.duration = duration;
        this.maxPlayers = maxPlayers;
        this.skillLevel = skillLevel;
        this.pricePerPlayer = pricePerPlayer;
    }

    public OpenPlay() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Court getCourt() {
        return court;
    }

    public void setCourt(Court court) {
        this.court = court;
    }

    public Integer getHost_id() {
        return host_id;
    }

    public void setHost_id(Integer host_id) {
        this.host_id = host_id;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public void setMaxPlayers(int maxPlayers) {
        this.maxPlayers = maxPlayers;
    }

    public int getMaxPlayers() {
        return maxPlayers;
    }

    public SkillLevel getSkillLevel() {
        return skillLevel;
    }

    public void setSkillLevel(SkillLevel skillLevel) {
        this.skillLevel = skillLevel;
    }

    public int getPricePerPlayer() {
        return pricePerPlayer;
    }

    public void setPricePerPlayer(int pricePerPlayer) {
        this.pricePerPlayer = pricePerPlayer;
    }
}
