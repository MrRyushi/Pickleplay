package com.example.pickleplay.user;

import com.example.pickleplay.Role;
import com.example.pickleplay.SkillLevel;
import jakarta.validation.constraints.NotEmpty;

public record UserDto(
        Integer id,
        @NotEmpty(message = "Name should not be empty")
        String name,
        String email,
        String password,
        Role role,
        SkillLevel skillLevel
) {
    @Override
    public Integer id() { return id; }
    @Override
    public String name() {
        return name;
    }

    @Override
    public String password() {
        return password;
    }

    @Override
    public SkillLevel skillLevel() {
        return skillLevel;
    }

    @Override
    public String email() {
        return email;
    }

    @Override
    public Role role() {
        return role;
    }
}
