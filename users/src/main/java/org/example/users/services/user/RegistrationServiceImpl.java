package org.example.users.services.user;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.users.dto.UserDto;
import org.example.users.model.User;
import org.example.users.model.enums.Role;
import org.example.users.repositories.UserRepository;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Slf4j
@AllArgsConstructor
public class RegistrationServiceImpl implements RegistrationService {
    private final UserRepository userRepository;


    @Override
    public boolean createUser(UserDto userDTO) {
        String email = userDTO.getEmail();
        if (isEmailAlreadyInUse(email)) {
            log.warn("Email already in use: {}", email);
            return false;
        }
        User user = buildUserFromDto(userDTO);
        userRepository.save(user);
        log.info("Saving new user with : {}", email);
        return true;
    }

    private User buildUserFromDto(UserDto userDto) {
        return User.builder()
                .email(userDto.getEmail())
                .firstName(userDto.getFirstName())
                .lastName(userDto.getLastName())
                .password(BCrypt.hashpw(userDto.getPassword(), BCrypt.gensalt()))
                .role(Role.USER)
                .active(true)
                .activationLink(UUID.randomUUID().toString())
                .build();
    }

    public boolean isEmailAlreadyInUse(String email) {
        boolean emailExists = userRepository.findByEmail(email).isPresent();
        if (emailExists) {
            log.warn("Email already in use: {}", email);
        }
        return emailExists;
    }
}