package org.example.users.model;

import lombok.Builder;
import lombok.Data;
import org.example.users.model.enums.Role;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@Document(collection = "users")
public class User {
    @Id
    private String id;
    private String email;
    private String firstName;
    private String lastName;
    private Role role;
    private String password;
    private Boolean active;
    private String activationLink;
    private String basketId;
    private String accessToken;
    private String refreshToken;
}
