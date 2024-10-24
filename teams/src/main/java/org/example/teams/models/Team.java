package org.example.teams.models;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Document(collection = "teams")
@Builder
public class Team {
    @Id
    private String teamId;
    private List<String> projectIds;
    private String name;
    private List<String> memberIds;
}
