package com.example.pickleplay.user;

import com.example.pickleplay.Role;

public record LoginResponseDto(
        Integer id,
        String name,
        String email,
        Role role,
        String token
) {
}
