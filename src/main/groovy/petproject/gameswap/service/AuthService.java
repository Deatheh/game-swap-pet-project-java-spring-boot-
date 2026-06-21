package petproject.gameswap.service;

import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import petproject.gameswap.dto.auth.AuthResponse;
import petproject.gameswap.dto.auth.RegisterRequest;
import petproject.gameswap.mapper.UserMapper;

@Service
public class AuthService {

    private static final Logger log = LoggerFactory.getLogger(AuthService.class);
    private final UserService userService;
    private final JwtService jwtService;
    private final UserMapper userMapper;
    private final RefreshTokenService refreshTokenService;

    public AuthService(UserService userService, JwtService jwtService, UserMapper userMapper, RefreshTokenService refreshTokenService) {
        this.userService = userService;
        this.jwtService = jwtService;
        this.userMapper = userMapper;
        this.refreshTokenService = refreshTokenService;
    }

    @Transactional
    public AuthResponse registration(RegisterRequest request) {
        var userEntity = userMapper.toEntity(request);

        log.info("Called userService.createUser");
        var user = userService.createUser(userEntity);

        log.info("Called jwtService.generateAccessToken");
        var accessToken = jwtService.generateAccessToken(user);

        log.info("Called jwtService.generateRefreshToken");
        var refreshToken = jwtService.generateRefreshToken(user);

        log.info("Called refreshTokenService.save");
        refreshTokenService.save(user.getId(),refreshToken);

        return new AuthResponse(
                accessToken,
                refreshToken
        );

    }

    //public AuthResponse login(LoginRequest request) {}
}
