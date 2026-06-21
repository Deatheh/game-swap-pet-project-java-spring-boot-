package petproject.gameswap.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import petproject.gameswap.dto.auth.AuthResponse;
import petproject.gameswap.dto.auth.RegisterRequest;
import petproject.gameswap.entity.Role;
import petproject.gameswap.entity.UserEntity;
import petproject.gameswap.mapper.UserMapper;

import java.time.LocalDateTime;

@Service
public class AuthService {

    private static final Logger log = LoggerFactory.getLogger(AuthService.class);
    private final UserService userService;
    private final JwtService jwtService;
    private final UserMapper userMapper;

    public AuthService(UserService userService, JwtService jwtService, UserMapper userMapper) {
        this.userService = userService;
        this.jwtService = jwtService;
        this.userMapper = userMapper;
    }

    public AuthResponse registration(RegisterRequest request) {
        var userEntity = userMapper.toDTO(request);

        log.info("Called userService.createUser");
        var user = userService.createUser(userEntity);

        log.info("Called jwtService.generateAccessToken");
        var accessToken = jwtService.generateAccessToken(user);

        log.info("Called jwtService.generateRefreshToken");
        var refreshToken = jwtService.generateRefreshToken(user);

        return new AuthResponse(
                accessToken,
                refreshToken
        );

    }

    //public AuthResponse login(LoginRequest request) {}
}
