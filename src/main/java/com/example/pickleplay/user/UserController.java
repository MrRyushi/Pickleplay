package com.example.pickleplay.user;

import com.example.pickleplay.security.JwtService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.security.core.Authentication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;
    public final JwtService jwtService;

    public UserController(UserService userService, JwtService jwtService) {
        this.userService = userService;
        this.jwtService = jwtService;
    }

    @PostMapping
    public UserResponseDto saveUser(
            @Valid @RequestBody UserDto dto
    ) {
        return this.userService.saveUser(dto);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDto dto) {

        User user = userService.login(dto);

        String token = jwtService.generateToken(user);

        return ResponseEntity.ok(
                new LoginResponseDto(
                        user.getId(),
                        user.getName(),
                        user.getEmail(),
                        user.getRole(),
                        token
                )
        );
    }

    @GetMapping("/{id}")
    public UserResponseDto getUserById(
            @PathVariable Integer id
    ){
        return this.userService.getUserById(id);
    }

    @GetMapping
    public List<UserResponseDto> getUsers(
            @RequestParam(required = false) String name
    ) {
        if (name != null) {
            return userService.getUsersByName(name);
        }
        return userService.getAllUsers();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(
            @PathVariable Integer id
    ) {
        this.userService.delete(id);
    }
}
