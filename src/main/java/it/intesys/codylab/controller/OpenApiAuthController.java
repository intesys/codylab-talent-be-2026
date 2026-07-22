package it.intesys.codylab.controller;

import it.intesys.codylab.controller.api.AuthControllerApi;
import it.intesys.codylab.controller.dto.LoginRequestApiDTO;
import it.intesys.codylab.controller.dto.RegisterRequestApiDTO;
import it.intesys.codylab.controller.dto.UserApiDTO;
import it.intesys.codylab.db.model.User;
import it.intesys.codylab.mapper.UserMapper;
import it.intesys.codylab.security.JwtService;
import it.intesys.codylab.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OpenApiAuthController implements AuthControllerApi {

    private final UserService userService;
    private final JwtService jwtService;
    private final UserMapper userMapper;

    public OpenApiAuthController(UserService userService, JwtService jwtService, UserMapper userMapper) {
        this.userService = userService;
        this.jwtService = jwtService;
        this.userMapper = userMapper;
    }

    @Override
    public ResponseEntity<UserApiDTO> registerUser(RegisterRequestApiDTO registerRequestApiDTO) {
        if (!registerRequestApiDTO.getPassword().equals(registerRequestApiDTO.getConfirmPassword())) {
            return ResponseEntity.badRequest().build();
        }
        try {
            User user = userService.register(
                    registerRequestApiDTO.getName(),
                    registerRequestApiDTO.getSurname(),
                    registerRequestApiDTO.getUsername(),
                    registerRequestApiDTO.getPassword()
            );
            String token = jwtService.generateToken(user.getUsername());
            UserApiDTO userDto = userMapper.mapToApiDto(user);
            userDto.setAccessToken(token);
            return ResponseEntity.status(HttpStatus.CREATED).body(userDto);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @Override
    public ResponseEntity<UserApiDTO> loginUser(LoginRequestApiDTO loginRequestApiDTO) {
        return userService.authenticate(loginRequestApiDTO.getUsername(), loginRequestApiDTO.getPassword())
                .map(user -> {
                    String token = jwtService.generateToken(user.getUsername());
                    UserApiDTO userDto = userMapper.mapToApiDto(user);
                    userDto.setAccessToken(token);
                    return ResponseEntity.ok(userDto);
                })
                .orElse(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
    }
}
