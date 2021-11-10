package movie.processing;

import movie.data.Actor;
import movie.data.Movie;
import movie.exception.MovieNotFoundException;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class Parse {
    static public Movie parse(String movieInfo) throws MovieNotFoundException {
        final Logger log = Logger.getLogger(Movie.class);
        Movie film = new Movie();
        if(!movieInfo.contains("\"Type\":\"movie\"")){
            log.error("Movie not found. Incorrect type");
            throw new MovieNotFoundException();
        }
        film.setTitle(movieInfo.split("Title\":\"")[1].split("\"")[0]); //title
        film.setImdbID(movieInfo.split("\"imdbID\":\"")[1].split("\"")[0]); //imdb ID
        film.setYear(Integer.parseInt(movieInfo.split("\"Year\":\"")[1].split("\"")[0])); //Year
        film.setPlot(movieInfo.split("\"Plot\":\"")[1].split("\",\"")[0]); //Plot
        film.setPosterUrl(movieInfo.split("\"Poster\":\"")[1].split("\",\"")[0]); //URL
        if(movieInfo.contains("Rotten Tomatoes\",\"Value\":\"")) {
            film.setTomatoMeter(Double.parseDouble("0." + movieInfo.split("Rotten Tomatoes\",\"Value\":\"")[1].split("%")[0]));
        }
        else {
            film.setTomatoMeter(0);
        }
        //награды и номинации
        String aAN = movieInfo.split("\"Awards\":\"")[1]; //работаем только с частью описания(awardsAndNominations)
        if  (aAN.contains("win")) {
            film.setAwardWins(Integer.parseInt(aAN.split("win")[0].split("\"")[
                    aAN.split("win")[0].split("\"").length - 1].split(" ")[
                    aAN.split("win")[0].split("\"")[
                            aAN.split("win")[0].split("\"").length - 1].split(" ").length - 1]
            ));//обращаемся к последнему элементу списка последнего элемента списка
        }
        else {
            film.setAwardWins(0);
        }

        if (aAN.contains("nomination")) {
            film.setAwardNominations(Integer.parseInt(aAN.split("nomination")[0].split(" ")[
                    aAN.split("nomination")[0].split(" ").length - 1]));//обращаемся к последнему элементу списка
        }
        else {
            film.setAwardNominations(0);
        }
//блок про актеров
        if(movieInfo.contains("\"Actors\":\"N/A\"")){
            film.setActors(null);
        }
        else {
            List<Actor> actors = new ArrayList<>();
            for(int i = 0; i < movieInfo.split("\"Actors\":\"")[1].split("\"")[0].split(", ").length; i++) {
                String infoAboutActor = movieInfo.split("\"Actors\":\"")[1].split("\"")[0].split(", ")[i];
                //System.out.println(infoAboutActor);
                Actor actor = new Actor();
                actor.setFirstName(infoAboutActor.split(" ")[0]);
                if(infoAboutActor.split(" ").length == 1) {
                    actor.setSecondName("NotStated");
                }
                else {
                    actor.setSecondName(infoAboutActor.substring(infoAboutActor.indexOf(" ")));
                }
                actors.add(actor);
            }
            film.setActors(actors);
        }
        if(movieInfo.contains("\"Response\":\"False\"")) {
            log.error("Movie not found. Movie does not exist");
            throw new MovieNotFoundException();
        }
        log.info("Information about the film has been processed");
        return film;
    }
}
