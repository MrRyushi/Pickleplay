package com.example.pickleplay.user;

import com.example.pickleplay.Role;
import com.example.pickleplay.SkillLevel;
import com.example.pickleplay.booking.Booking;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(
        name = "users"
)
public class User {


    @Id
    @GeneratedValue
    private Integer id;
    private String name;
    @Column(
            unique = true
    )
    private String email;
    private String password;
    private Role role;
    private SkillLevel skillLevel;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Booking> booking;

    public User() {
    }

    public User(Integer id, String name, String email, String password, Role role, SkillLevel skillLevel) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
        this.skillLevel = skillLevel;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public SkillLevel getSkillLevel() {
        return skillLevel;
    }

    public void setSkillLevel(SkillLevel skillLevel) {
        this.skillLevel = skillLevel;
    }
}
