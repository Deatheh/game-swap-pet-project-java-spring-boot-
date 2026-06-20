package petproject.gameswap.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import petproject.gameswap.entity.UserEntity;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByEmail(String email);
    Optional<UserEntity> findByUsername(String username);
}
