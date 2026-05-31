package com.example.pickleplay.user;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {
    private final UserRepository repository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository repository, UserMapper userMapper, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
    }

    public UserResponseDto saveUser(
            UserDto dto
    ){
        var user = userMapper.toUser(dto);
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        var savedUser = repository.save(user);
        return userMapper.toUserResponseDto(savedUser);
    }

    public User login(LoginDto dto){
        var user = repository.findByEmail(dto.email())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid credentials"));

        if (!passwordEncoder.matches(dto.password().trim(), user.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }

        return user;
    }

    public List<UserResponseDto> getAllUsers(){
        return repository.findAll()
                .stream()
                .map(userMapper::toUserResponseDto)
                .collect(Collectors.toList());
    }

    public UserResponseDto getUserById(Integer id){
        return repository.findById(id)
                .map(userMapper::toUserResponseDto)
                .orElse(null);
    }

    public List<UserResponseDto> getUsersByName(String name){
        return repository.findByNameContainingIgnoreCase(name)
                .stream()
                .map(userMapper::toUserResponseDto)
                .collect(Collectors.toList());
    }


    public void delete(Integer id){
        repository.deleteById(id);
    }
}
