package dao;

import model.ActorEntity;
import model.MovieEntity;
import movie.data.Actor;
import movie.data.Movie;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class WorkWithDB {
    static public void setActorsInDB (@NotNull List<List<Actor>> actorInFavorite, List<Movie> favoriteFilms) {

        try (SessionFactory factory = new Configuration().configure().buildSessionFactory()) {
            DAO<ActorEntity, String> ActorEntityDAO = new ActorEntityDAO(factory);

            for (int i = 0; i < actorInFavorite.size(); i++) {
                if (actorInFavorite.get(i) != null) {
                    for (int j = 0; j < actorInFavorite.get(i).size(); j++) {
                        final ActorEntity ActorEntity = new ActorEntity();
                        ActorEntity.setActor_id(favoriteFilms.get(i).getImdbID() + j);
                        ActorEntity.setFilm_id(favoriteFilms.get(i).getImdbID());
                        ActorEntity.setFirst_name(actorInFavorite.get(i).get(j).getFirstName());
                        ActorEntity.setSecond_name(actorInFavorite.get(i).get(j).getSecondName());
                        ActorEntityDAO.create(ActorEntity);
                    }
                }
            }
        }
    }

    static public void removeMovieFromDB(@NotNull Movie movie) {
        try (SessionFactory factory = new Configuration().configure().buildSessionFactory()) {
            DAO<MovieEntity, String> MovieEntityDAO = new MovieEntityDAO(factory);
            MovieEntityDAO.delete(new MovieEntity(movie.getImdbID(),
                    movie.getTitle(), movie.getYear(), movie.getPlot(), movie.getPosterUrl(),
                    movie.getAwardWins(), movie.getAwardNominations(), movie.getTomatoMeter()));
        }
    }

    static public void setMovieInDB(Movie movie) {
        try (SessionFactory factory = new Configuration().configure().buildSessionFactory()) {
            DAO<MovieEntity, String> MovieEntityDAO = new MovieEntityDAO(factory);

            final MovieEntity MovieEntity = new MovieEntity();
            MovieEntity.setImdb_id(movie.getImdbID());
            MovieEntity.setTitle(movie.getTitle());
            MovieEntity.setYear(movie.getYear());
            MovieEntity.setPlot(movie.getPlot());
            MovieEntity.setPoster_url(movie.getPosterUrl());
            MovieEntity.setAward_wins(movie.getAwardWins());
            MovieEntity.setAward_nominations(movie.getAwardNominations());
            MovieEntity.setTomato_meter(movie.getTomatoMeter());
            MovieEntityDAO.create(MovieEntity);
        }
    }
}
