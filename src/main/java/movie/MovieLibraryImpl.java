package movie;

import dao.WorkWithDB;
import movie.processing.GetURL;
import movie.data.Movie;
import movie.processing.Parse;
import movie.exception.MovieNotFoundException;
import org.apache.log4j.Logger;

import java.util.*;

public class MovieLibraryImpl implements MovieLibrary{
    private static final Logger log = Logger.getLogger(MovieLibraryImpl.class);
    Set<String> favoriteMoviesID = new HashSet<>();
    String apikey = "1e575fe4";
    String type = "movie";
    GetURL url = new GetURL();

    @Override
    public List<Movie> findMoviesByName(String movieName) throws MovieNotFoundException {
        String newMovieName = movieName.replace(" ", "+");
        List<Movie> movies = new ArrayList<>();
        int page = 1;
        while (true) {
                String movieInfo = url.getURL(newMovieName, null, apikey, type, page);
                if(movieInfo.contains("\"Response\":\"False\"")) {break;}
                String[] movieList = movieInfo.split("\\{");
                for (int i = 2; i < movieList.length; i++)
                    movies.add(getMovieById(movieList[i].split("\"imdbID\":\"")[1].split("\"")[0]));//добавляем фильмы
            page += 1;
        }
        log.info("List of movies has been created");
        return movies;
    }

    @Override
    public Movie getMovieById(String imdbId) throws MovieNotFoundException {
        GetURL url = new GetURL();
        String movieInfo = url.getURL(null, imdbId, apikey, type, 1);
        return Parse.parse(movieInfo);
    }

    @Override
    public boolean addMovieToFavoriteByName(String movieName) throws MovieNotFoundException {
        boolean contain = false;
        List<Movie> favoriteMovie = findMoviesByName(movieName);
        for (int i = 0; i < favoriteMovie.size(); i++) {
            if (favoriteMoviesID.contains(favoriteMovie.get(i).getImdbID())) {
                log.info("Movie is already in the favorite movies");
                contain = true;
            } else {
                favoriteMoviesID.add(favoriteMovie.get(i).getImdbID());
                WorkWithDB.setMovieInDB(getMovieById(favoriteMovie.get(i).getImdbID()));
                log.info("Movie has been added to the list of favorite movies by name");
            }
        }
        return contain;
    }

    @Override
    public boolean addMovieToFavoriteById(String imdbId) throws MovieNotFoundException {
        if(favoriteMoviesID.contains(getMovieById(imdbId).getImdbID())) {
            log.info("Movie is already in the favorite movies");
            return true;
        }
        else {
            favoriteMoviesID.add(getMovieById(imdbId).getImdbID());
            WorkWithDB.setMovieInDB(this.getMovieById(imdbId));
            log.info("Movie has been added to the list of favorite movies by imdbId");
            return false;
        }
    }

    @Override
    public List<Movie> getFavoriteFilms() {
        List<Movie> favoriteMovies = new ArrayList<Movie>();
        Iterator<String> it = favoriteMoviesID.iterator();
        while(it.hasNext()) {
            try {
                favoriteMovies.add(getMovieById(it.next()));
            }
            catch (MovieNotFoundException e) {
                log.error("Incorrect movie imdbId in the List<Movie>");
            }
        }
        log.info("Got a list of favorite movies");
        return favoriteMovies;
    }

    @Override
    public List<Movie> getFavoriteFilmsWithActor(String fullName) {
        if(favoriteMoviesID == null) {
            return null;
        }
        List<Movie> movies = getFavoriteFilms();
        List<Movie> movieWithActors = new ArrayList<>();
        for (int i = 0; i < movies.size(); i++) {
            if(movies.get(i).getActors() != null) {
                for (int j = 0; j < movies.get(i).getActors().size(); j++) {
                    if ((movies.get(i).getActors().get(j).getFirstName() + movies.get(i).getActors().get(j).getSecondName()).equals(fullName)) {
                        movieWithActors.add(movies.get(i));
                        break;
                    }
                }
            }
        }
        log.info("Got a list of favorite movies with chosen actors");
        return movieWithActors;
    }

    @Override
    public void removeMovieFromFavorite(String imdbId) throws MovieNotFoundException {
        WorkWithDB.removeMovieFromDB(this.getMovieById(imdbId));
        if (favoriteMoviesID.contains(imdbId)) {
            favoriteMoviesID.remove(imdbId);
            log.info("Movie removed");
        }
        else {
            log.error("Movie is not favorite");
            throw new MovieNotFoundException();
        }
    }

    @Override
    public Movie getRandomMovie() {
        Movie randomMovie;
        try {
            String random = String.valueOf(Math.random());
            randomMovie = getMovieById("tt" + random.charAt(2) + random.charAt(3) + random.charAt(4) +
                    random.charAt(5) + random.charAt(6) + random.charAt(7) + random.charAt(8));
        }
        catch (MovieNotFoundException e) {
            randomMovie = getRandomMovie();
        }
        log.info("Got a random movie");
        return randomMovie;
    }
}
