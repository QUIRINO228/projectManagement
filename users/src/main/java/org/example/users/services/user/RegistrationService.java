package org.example.users.services.user;

import org.example.users.dto.UserDto;
import org.springframework.stereotype.Service;

@Service
public interface RegistrationService {

    boolean createUser(UserDto userDTO);

}
