package petproject.gameswap.mapper;

import org.springframework.stereotype.Component;
import petproject.gameswap.dto.auth.RegisterRequest;
import petproject.gameswap.entity.Role;
import petproject.gameswap.entity.UserEntity;

import java.time.LocalDateTime;

@Component
public class UserMapper {

    public UserEntity toEntity(RegisterRequest request){

        return UserEntity.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .password(request.getPassword())
                .role(Role.USER)
                .createdAt(LocalDateTime.now())
                .isActive(true)
                .build();
    }
}
