package org.example.users.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.users.model.enums.Role;

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