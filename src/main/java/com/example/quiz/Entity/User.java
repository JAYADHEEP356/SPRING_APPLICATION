package com.example.quiz.Entity;

import jakarta.persistence.*;

import org.hibernate.annotations.ColumnDefault;

import java.time.Duration;
import java.time.Instant;


@Entity
@Table(name = "users")
public class User {
    @Id
    @Column(name = "username", nullable = false, length = 50)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @ColumnDefault("1")
    @Column(name = "enabled", nullable = false)
    private Boolean enabled = false;

    @Column(name = "email", nullable = false, length = 100)
    private String email;

    @Column(name = "start_Time")
    private Instant startTime;

    @Column(name = "end_Time")
    private Instant endTime;

    @Column(name = "total_Time")
    private Long totalTime;

    @ColumnDefault("0")
    @Column(name = "timeup", nullable = false)
    private Boolean timeup = false;

    public Boolean getTimeup() {
        return timeup;
    }

    public void setTimeup(Boolean timeup) {
        this.timeup = timeup;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Instant getStartTime() {
        return startTime;
    }

    public void setStartTime(Instant startTime) {
        this.startTime = startTime;
    }

    public Instant getEndTime() {
        return endTime;
    }

    public Long getTotalTime() {
        return totalTime;
    }

    public void setTotalTime(long totalTime) {
        this.totalTime = totalTime;
    }

    public void setEndTime(Instant endTime) {
        this.endTime = endTime;
    }

}