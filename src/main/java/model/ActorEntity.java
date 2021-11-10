package model;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ActorEntity {

    private String first_name;
    private String second_name;
    private String actor_id;
    private String film_id;
}
