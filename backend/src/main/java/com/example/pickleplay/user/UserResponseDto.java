package com.example.pickleplay.user;

import com.example.pickleplay.Role;
import com.example.pickleplay.SkillLevel;

public record UserResponseDto(
        Integer id,
        String name,
        String email,
        Role role,
        SkillLevel skillLevel
) {
}
