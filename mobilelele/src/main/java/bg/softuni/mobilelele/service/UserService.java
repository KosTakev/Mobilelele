package bg.softuni.mobilelele.service;

import bg.softuni.mobilelele.model.dto.UserLoginDto;
import bg.softuni.mobilelele.model.entity.UserEntity;
import bg.softuni.mobilelele.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private Logger LOGGER = LoggerFactory.getLogger(UserService.class);
    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public boolean login(UserLoginDto loginDto) {


        Optional<UserEntity> userOpt = userRepository.findByEmail(loginDto.getUsername());

        if(userOpt.isEmpty()) {
            LOGGER.info("User with name [{}] not found", loginDto.getUsername());
            return false;
        }

        return userOpt.get().getPassword().equals(loginDto.getPassword());
    }
}
