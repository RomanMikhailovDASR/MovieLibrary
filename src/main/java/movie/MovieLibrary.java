package movie;

import movie.data.Actor;
import movie.data.Movie;
import movie.exception.MovieNotFoundException;

import java.util.List;

public interface MovieLibrary {
    /**
     * Finds movies using by part of its name
     * @param movieName - search string
     * @return list of found movies
     */
    List<Movie> findMoviesByName(String movieName) throws MovieNotFoundException;

    /**
     * Loads movie by its imdb id
     * @param imdbId - id of movie
     * @return movie information
     */
    Movie getMovieById(String imdbId) throws MovieNotFoundException;

    /**
     * Adds film in personal collection by full movie name
     * @param movieName - name of movie
     * @return true if this film was already in collection, false - otherwise
     */
    boolean addMovieToFavoriteByName(String movieName) throws MovieNotFoundException;

    /**
     * Adds film in personal collection by imdb id
     * @param imdbId - id of film
     * @return true if this film was already in collection, false - otherwise
     */
    boolean addMovieToFavoriteById(String imdbId) throws MovieNotFoundException;

    /**
     * Loads collection of favorite movies
     * @return list of movies
     */
    List<Movie> getFavoriteFilms();

    /**
     * Gets films from personal collections which have specified actor
     * @param fullName - first and second name of actor
     * @return list of movies
     */
    List <Movie> getFavoriteFilmsWithActor(String fullName);

    /**
     * Removes film from collection of favorite movies
     * @param imdbId - id of deleted movie
     */
    void removeMovieFromFavorite(String imdbId) throws MovieNotFoundException;


    /**
     * Loads random movie from movie database
     * @return movie information
     */
    Movie getRandomMovie();
}
