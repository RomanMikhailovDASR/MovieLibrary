package model;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MovieEntity {

    private String imdb_id;
    private String title;
    private int year;
    private String plot;
    private String poster_url;
    private int award_wins;
    private int award_nominations;
    private double tomato_meter;
}