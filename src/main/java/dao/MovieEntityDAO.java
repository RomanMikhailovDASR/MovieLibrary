package dao;

import com.sun.istack.NotNull;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
//import org.jetbrains.annotations.NotNull;
import model.MovieEntity;

public class MovieEntityDAO implements dao.DAO<MovieEntity, String> {

    private final SessionFactory factory;

    public MovieEntityDAO(@NotNull final SessionFactory factory) {
        this.factory = factory;
    }

    @Override
    public void create(@NotNull final MovieEntity MovieEntity) {
        try (final Session session = factory.openSession()) {
            session.beginTransaction();
            session.save(MovieEntity);
            session.getTransaction().commit();
        }
    }

    @Override
    public MovieEntity read(@NotNull final String imdbID) {
        try (final Session session = factory.openSession()) {
            final MovieEntity result = session.get(MovieEntity.class, imdbID);
            return result != null ? result : new MovieEntity();
        }
    }

    @Override
    public void update(@NotNull final MovieEntity MovieEntity) {
        try (Session session = factory.openSession()) {
            session.beginTransaction();
            session.update(MovieEntity);
            session.getTransaction().commit();
        }
    }

    @Override
    public void delete(@NotNull final MovieEntity MovieEntity) {
        try (Session session = factory.openSession()) {
            session.beginTransaction();
            session.delete(MovieEntity);
            session.getTransaction().commit();
        }
    }
}
