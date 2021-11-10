package tests;

import movie.MovieLibrary;
import movie.MovieLibraryImpl;
import movie.processing.GetURL;
import movie.data.Movie;
import movie.processing.Parse;
import movie.exception.MovieNotFoundException;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class MovieLibraryImplMockTest {

    MovieLibrary movieLibrary = new MovieLibraryImpl();

    @Test //Тест фильма с наградами и номинациями
    public void MockTest1 () throws MovieNotFoundException{
        Movie correctMovie = new Movie();
        correctMovie.setTomatoMeter(0.96);
        correctMovie.setAwardWins(22);
        correctMovie.setAwardNominations(25);
        correctMovie.setPlot("Marty McFly, a 17-year-old high school student, is accidentally sent thirty years into the past in a time-traveling DeLorean invented by his close friend, the eccentric scientist Doc Brown.");
        correctMovie.setYear(1985);
        correctMovie.setTitle("Back to the Future");
        correctMovie.setPosterUrl("https://m.media-amazon.com/images/M/MV5BZmU0M2Y1OGUtZjIxNi00ZjBkLTg1MjgtOWIyNThiZWIwYjRiXkEyXkFqcGdeQXVyMTQxNzMzNDI@._V1_SX300.jpg");
        correctMovie.setImdbID("tt0088763");

        GetURL url = mock(GetURL.class);
        when(url.getURL(null, "tt0088763", "1e575fe4", "movie", 1)).thenReturn("\"Title\":\"Back to the Future\",\"Year\":\"1985\",\"Rated\":\"PG\",\"Released\":\"03 Jul 1985\",\"Runtime\":\"116 min\",\"Genre\":\"Adventure, Comedy, Sci-Fi\",\"Director\":\"Robert Zemeckis\",\"Writer\":\"Robert Zemeckis, Bob Gale\",\"Actors\":\"Michael J. Fox, Christopher Lloyd, Lea Thompson\",\"Plot\":\"Marty McFly, a 17-year-old high school student, is accidentally sent thirty years into the past in a time-traveling DeLorean invented by his close friend, the eccentric scientist Doc Brown.\",\"Language\":\"English\",\"Country\":\"United States\",\"Awards\":\"Won 1 Oscar. 22 wins & 25 nominations total\",\"Poster\":\"https://m.media-amazon.com/images/M/MV5BZmU0M2Y1OGUtZjIxNi00ZjBkLTg1MjgtOWIyNThiZWIwYjRiXkEyXkFqcGdeQXVyMTQxNzMzNDI@._V1_SX300.jpg\",\"Ratings\":[{\"Source\":\"Internet Movie Database\",\"Value\":\"8.5/10\"},{\"Source\":\"Rotten Tomatoes\",\"Value\":\"96%\"},{\"Source\":\"Metacritic\",\"Value\":\"87/100\"}],\"Metascore\":\"87\",\"imdbRating\":\"8.5\",\"imdbVotes\":\"1,112,107\",\"imdbID\":\"tt0088763\",\"Type\":\"movie\",\"DVD\":\"17 Aug 2010\",\"BoxOffice\":\"$211,406,762\",\"Production\":\"N/A\",\"Website\":\"N/A\",\"Response\":\"True\"}");
        assertEquals(correctMovie, Parse.parse(url.getURL(null, "tt0088763", "1e575fe4", "movie", 1)));
    }

    @Test //Тест фильма без наград и номинаций
    public void MockTest2 () throws MovieNotFoundException{
        Movie correctMovie = new Movie();
        correctMovie.setTomatoMeter(0.71);
        correctMovie.setAwardWins(0);
        correctMovie.setAwardNominations(0);
        correctMovie.setPlot("A fun weekend turns into madness and horror for a bunch of groupies looking for fun in a beaver infested swamp.");
        correctMovie.setYear(2014);
        correctMovie.setTitle("Zombeavers");
        correctMovie.setPosterUrl("https://m.media-amazon.com/images/M/MV5BNTMzMzc4ODc1M15BMl5BanBnXkFtZTgwMTM0MTgxMTE@._V1_SX300.jpg");
        correctMovie.setImdbID("tt2784512");

        GetURL url = mock(GetURL.class);
        when(url.getURL(null, "tt2784512", "1e575fe4", "movie", 1)).thenReturn("\"Title\":\"Zombeavers\",\"Year\":\"2014\",\"Rated\":\"R\",\"Released\":\"20 Mar 2015\",\"Runtime\":\"77 min\",\"Genre\":\"Action, Comedy, Fantasy\",\"Director\":\"Jordan Rubin\",\"Writer\":\"Al Kaplan, Jordan Rubin, Jon Kaplan\",\"Actors\":\"Rachel Melvin, Cortney Palm, Lexi Atkins\",\"Plot\":\"A fun weekend turns into madness and horror for a bunch of groupies looking for fun in a beaver infested swamp.\",\"Language\":\"English\",\"Country\":\"United States\",\"Awards\":\"N/A\",\"Poster\":\"https://m.media-amazon.com/images/M/MV5BNTMzMzc4ODc1M15BMl5BanBnXkFtZTgwMTM0MTgxMTE@._V1_SX300.jpg\",\"Ratings\":[{\"Source\":\"Internet Movie Database\",\"Value\":\"4.8/10\"},{\"Source\":\"Rotten Tomatoes\",\"Value\":\"71%\"},{\"Source\":\"Metacritic\",\"Value\":\"44/100\"}],\"Metascore\":\"44\",\"imdbRating\":\"4.8\",\"imdbVotes\":\"17,652\",\"imdbID\":\"tt2784512\",\"Type\":\"movie\",\"DVD\":\"19 May 2015\",\"BoxOffice\":\"$14,947\",\"Production\":\"N/A\",\"Website\":\"N/A\",\"Response\":\"True\"");
        assertEquals(correctMovie, Parse.parse(url.getURL(null, "tt2784512", "1e575fe4", "movie", 1)));
    }
}
