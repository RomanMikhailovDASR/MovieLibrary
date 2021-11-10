package tests;

import movie.MovieLibrary;
import movie.MovieLibraryImpl;
import movie.data.Movie;
import movie.exception.MovieNotFoundException;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class MovieLibraryImplTest {
    MovieLibrary movieLibrary = new MovieLibraryImpl();
    @Test
    public void testGetMovieById () throws MovieNotFoundException {
        Movie correctMovie = new Movie();
        correctMovie.setTomatoMeter(0.96);
        correctMovie.setAwardWins(22);
        correctMovie.setAwardNominations(25);
        correctMovie.setPlot("Marty McFly, a 17-year-old high school student, is accidentally sent thirty years into the past in a time-traveling DeLorean invented by his close friend, the eccentric scientist Doc Brown.");
        correctMovie.setYear(1985);
        correctMovie.setTitle("Back to the Future");
        correctMovie.setPosterUrl("https://m.media-amazon.com/images/M/MV5BZmU0M2Y1OGUtZjIxNi00ZjBkLTg1MjgtOWIyNThiZWIwYjRiXkEyXkFqcGdeQXVyMTQxNzMzNDI@._V1_SX300.jpg");
        correctMovie.setImdbID("tt0088763");

        Movie testMovie = movieLibrary.getMovieById("tt0088763");
        Assert.assertEquals(correctMovie, testMovie);
    }

    @Test //тест с переходом на следующую страницу в запросе
    public void testFindMoviesByName () throws MovieNotFoundException {
        List<Movie> correctListMovie = new ArrayList<Movie>();

        correctListMovie.add(movieLibrary.getMovieById("tt0120201"));
        correctListMovie.add(movieLibrary.getMovieById("tt0844760"));
        correctListMovie.add(movieLibrary.getMovieById("tt0367093"));
        correctListMovie.add(movieLibrary.getMovieById("tt2085930"));
        correctListMovie.add(movieLibrary.getMovieById("tt6977240"));
        correctListMovie.add(movieLibrary.getMovieById("tt3124260"));
        correctListMovie.add(movieLibrary.getMovieById("tt0310988"));
        correctListMovie.add(movieLibrary.getMovieById("tt0326211"));
        correctListMovie.add(movieLibrary.getMovieById("tt3186314"));
        correctListMovie.add(movieLibrary.getMovieById("tt7200508"));
        correctListMovie.add(movieLibrary.getMovieById("tt14859702"));
        correctListMovie.add(movieLibrary.getMovieById("tt14859702"));

        List<Movie> testListMovie = movieLibrary.findMoviesByName("Starship Troopers");
        Assert.assertEquals(correctListMovie, testListMovie);
    }

    @Test //одновременная проверка добавления фильма по Id и по названию
    public void testGetFavoriteFilms () throws MovieNotFoundException {
        List<Movie> correctListMovie = new ArrayList<Movie>();;

        movieLibrary.addMovieToFavoriteByName("Scouts Guide to the Zombie Apocalypse");
        movieLibrary.addMovieToFavoriteById("tt14859702");
        List<Movie> testListMovie = movieLibrary.getFavoriteFilms();
        correctListMovie.add(movieLibrary.getMovieById("tt1727776"));
        correctListMovie.add(movieLibrary.getMovieById("tt14859702"));
        Assert.assertEquals(correctListMovie, testListMovie);
    }

    @Test
    public void testRemoveMovieFromFavorite () throws MovieNotFoundException {
        List<Movie> correctListMovie = new ArrayList<Movie>();;

        movieLibrary.addMovieToFavoriteByName("Zombeavers");
        movieLibrary.addMovieToFavoriteById("tt0104237");
        movieLibrary.removeMovieFromFavorite("tt2784512");
        List<Movie> testListMovie = movieLibrary.getFavoriteFilms();

        correctListMovie.add(movieLibrary.getMovieById("tt0104237"));
        Assert.assertEquals(correctListMovie, testListMovie);
    }

    @Test
    public void testGetFavoriteFilmsWithActor () throws MovieNotFoundException {
        List<Movie> correctListMovie = new ArrayList<Movie>();

        movieLibrary.addMovieToFavoriteByName("Fight Club");
        List<Movie> testListMovie = movieLibrary.getFavoriteFilmsWithActor("Brad Pitt");

        correctListMovie.add(movieLibrary.getMovieById("tt0137523"));
        correctListMovie.add(movieLibrary.getMovieById("tt0373207"));
        correctListMovie.add(movieLibrary.getMovieById("tt5270994"));
        Assert.assertEquals(correctListMovie, testListMovie);
    }
}
