package bg.softuni.mobilelele.service;

import bg.softuni.mobilelele.model.dto.UserLoginDto;
import bg.softuni.mobilelele.model.dto.UserRegisterDto;
import bg.softuni.mobilelele.model.entity.UserEntity;
import bg.softuni.mobilelele.model.mapper.UserMapper;
import bg.softuni.mobilelele.repository.UserRepository;
import bg.softuni.mobilelele.user.CurrentUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private Logger LOGGER = LoggerFactory.getLogger(UserService.class);
    private UserRepository userRepository;
    private CurrentUser currentUser;
    private PasswordEncoder passwordEncoder;
    private UserMapper userMapper;

    public UserService(UserRepository userRepository,
                       CurrentUser currentUser,
                       PasswordEncoder passwordEncoder,
                       UserMapper userMapper) {
        this.userRepository = userRepository;
        this.currentUser = currentUser;
        this.passwordEncoder = passwordEncoder;
        this.userMapper = userMapper;
    }

    public void registerAndLogin(UserRegisterDto userRegisterDto) {

        UserEntity newUser = userMapper.userDtoToUserEntity(userRegisterDto);
        newUser.setPassword(passwordEncoder.encode(userRegisterDto.getPassword()));

            userRepository.save(newUser);
            login(newUser);
    }

    public boolean login(UserLoginDto loginDto) {


        Optional<UserEntity> userOpt = userRepository.findByEmail(loginDto.getUsername());

        if (userOpt.isEmpty()) {
            LOGGER.info("User with name [{}] not found", loginDto.getUsername());
            return false;
        }

        var rawPassword = loginDto.getPassword();
        var encodedPassword = userOpt.get().getPassword();

        boolean success = passwordEncoder.matches(rawPassword, encodedPassword);

        if (success) {
            login(userOpt.get());
        } else {
            logout();
        }

        return success;
    }

    private void login(UserEntity userEntity) {
        currentUser
                .setLoggedIn(true)
                .setName(userEntity.getFirstName() + " " + userEntity.getLastName());
    }

    public void logout() {
        currentUser.clear();
    }
}
