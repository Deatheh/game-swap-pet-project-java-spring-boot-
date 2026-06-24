package petproject.gameswap.controller;

import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import petproject.gameswap.dto.auth.AuthResponse;
import petproject.gameswap.dto.auth.LoginRequest;
import petproject.gameswap.dto.auth.RefreshRequest;
import petproject.gameswap.dto.auth.RegisterRequest;
import petproject.gameswap.service.AuthService;
import petproject.gameswap.service.JwtService;
import petproject.gameswap.service.UserService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private static final Logger log = LoggerFactory.getLogger(AuthController.class);
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }


    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(
            @Valid @RequestBody RegisterRequest request
    ){
        log.info("Called authService.registration");
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(authService.registration(request));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(
            @Valid @RequestBody LoginRequest request
    ){
        return ResponseEntity.status(HttpStatus.OK)
                .body(authService.login(request));
    }

    /*@PostMapping("/refresh")
    public ResponseEntity<AuthResponse> refresh(
            @Valid @RequestBody RefreshRequest request
    ){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(authService.refrash(request));
    }*/
}


