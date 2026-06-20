package petproject.gameswap.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import petproject.gameswap.entity.UserEntity;
import petproject.gameswap.exception.EmailAlreadyExistsException;
import petproject.gameswap.exception.UsernameAlreadyExistsException;
import petproject.gameswap.repository.UserRepository;
import petproject.gameswap.utils.EmailFunctions;


@Service
public class UserService {

    private static final Logger log = LoggerFactory.getLogger(UserService.class);
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
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


    /*public Boolean isPasswordCorrect(String email, String password) {
        if (!isUserExists(email)) { throw  new NoSuchElementException("User with email " + email + " don't exists!"); }
        return true;
    }

    private Boolean isUserExists(String email) {
        return true;
    }*/
}
