package org.example.authenticationservice.model;

import lombok.*;
import org.example.authenticationservice.model.enums.Role;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private String id;
    private String email;
    private String firstName;
    private String lastName;
    private String password;
    private Role role;
}