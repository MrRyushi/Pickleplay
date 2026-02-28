package com.example.pickleplay.user;

import org.springframework.stereotype.Service;

@Service
public class UserMapper {
    public User toUser(UserDto dto) {
        var user = new User();
        user.setId(dto.id());
        user.setName(dto.name());
        user.setEmail(dto.email());
        user.setRole(dto.role());
        user.setSkillLevel(dto.skillLevel());

        return user;
    }

    public UserResponseDto toUserResponseDto(User user){
        return new UserResponseDto(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getRole(),
                user.getSkillLevel()
        );
    }
}
