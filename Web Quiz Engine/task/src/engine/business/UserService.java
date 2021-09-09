package engine.business;

import engine.persistence.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    private final PasswordEncoder getEncoder;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder getEncoder) {
        this.userRepository = userRepository;
        this.getEncoder = getEncoder;
    }

    public User registerNewUserAccount(User user) {
        Optional<User> findUser = userRepository.findByEmail(user.getEmail());
        if (findUser.isPresent()) {
            return null;
        } else {
            user.setRoles("ROLE_USER");
            user.setActive(true);
            user.setPassword(getEncoder.encode(user.getPassword()));
            return userRepository.save(user);
        }
    }

    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email).orElse(null);
    }


    public User save(User user) {
        return userRepository.save(user);
    }
}
