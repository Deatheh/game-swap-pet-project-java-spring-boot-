package petproject.gameswap.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import petproject.gameswap.dto.auth.LoginRequest;
import petproject.gameswap.entity.UserEntity;
import petproject.gameswap.exception.*;
import petproject.gameswap.repository.UserRepository;


@Service
public class UserService {

    private static final Logger log = LoggerFactory.getLogger(UserService.class);
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }


    public UserEntity createUser(UserEntity user) {
        if (userRepository.findByEmail(user.getEmail()).isPresent()){
            log.warn("Error(409): Email already exists");
            throw new EmailAlreadyExistsException("User with email " + user.getEmail() + " already exists");
        }
        if (userRepository.findByUsername(user.getUsername()).isPresent()){
            log.warn("Error(409): username already exists");
            throw new UsernameAlreadyExistsException("User with username " + user.getUsername() + " already exists");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public UserEntity getUserByEmail(String email){
        var user = userRepository.findByEmail(email);
        if (user.isEmpty()) {
            log.warn("Error(404): email not exists");
            throw new EmailNotFoundException("user with email: " + email + " not found");
        }
        return user.get();
    }

    public void verificationByPassword(UserEntity user, String password){
        if (!passwordEncoder.matches(password, user.getPassword())) {
            log.warn("Error(401): incorrect password");
            throw new IncorrectPasswordException("incorrect password");
        }
    }

    public void  verificationUserActive(UserEntity user){
        if (!user.getIsActive()){
            log.warn("Error(401): user is not active");
            throw new UnauthorizedException("User is not active");
        }
    }


    /*public Boolean isPasswordCorrect(String email, String password) {
        if (!isUserExists(email)) { throw  new NoSuchElementException("User with email " + email + " don't exists!"); }
        return true;
    }

    private Boolean isUserExists(String email) {
        return true;
    }*/
}
