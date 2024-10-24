package org.example.teams.models;

import lombok.Builder;
import lombok.Data;
import org.example.teams.models.enums.Role;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "teams")
@Builder
public class TeamMember {
    @Id
    private String memberId;
    private String teamId;
    private String userId;
    private Role role;
}
