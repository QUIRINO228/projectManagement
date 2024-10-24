package org.example.authenticationservice.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.authenticationservice.model.enums.Role;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {
    private String id;
    private String email;
    private String firstName;
    private String lastName;
    private Role role;
    private String password;
    private Boolean active;
    private String activationLink;
    private String basketId;
}
