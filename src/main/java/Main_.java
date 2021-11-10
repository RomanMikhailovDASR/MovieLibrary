import dao.WorkWithDB;
import movie.MovieLibrary;
import movie.MovieLibraryImpl;
import movie.data.Actor;
import movie.exception.MovieNotFoundException;

import java.util.ArrayList;
import java.util.List;

public class Main_ {

    public static void main(String[] args) throws MovieNotFoundException {
        MovieLibrary matrix = new MovieLibraryImpl();
        matrix.addMovieToFavoriteByName("The Shawshank Redemption");

        List<List<Actor>> actors = new ArrayList<>();
        for(int i = 0; i < matrix.getFavoriteFilms().size(); i++) {
            actors.add(matrix.getFavoriteFilms().get(i).getActors());
        }

        WorkWithDB.setActorsInDB(actors, matrix.getFavoriteFilms());
    }
}
