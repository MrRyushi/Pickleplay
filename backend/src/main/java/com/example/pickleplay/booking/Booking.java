package com.example.pickleplay.booking;

import com.example.pickleplay.Status;
import com.example.pickleplay.court.Court;
import com.example.pickleplay.user.User;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Booking {
    @Id
    @GeneratedValue
    private Integer id;
    @Column(
            nullable = false
    )
    private LocalDateTime startTime;
    @Column(
            nullable = false
    )
    private LocalDateTime endTime;
    private Status status;
    @ManyToOne
    @JoinColumn(name = "court_id")
    private Court court;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Booking(Integer id, LocalDateTime startTime, LocalDateTime endTime, Status status, Court court, User user) {
        this.id = id;
        this.startTime = startTime;
        this.endTime = endTime;
        this.status = status;
        this.court = court;
        this.user = user;
    }

    public Booking() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Court getCourt() {
        return court;
    }

    public void setCourt(Court court) {
        this.court = court;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
