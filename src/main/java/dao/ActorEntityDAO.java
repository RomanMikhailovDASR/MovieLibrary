package dao;

import com.sun.istack.NotNull;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
//import org.jetbrains.annotations.NotNull;
import model.ActorEntity;

public class ActorEntityDAO implements dao.DAO<ActorEntity, String> {

    private final SessionFactory factory;

    public ActorEntityDAO(@NotNull final SessionFactory factory) {
        this.factory = factory;
    }

    @Override
    public void create(@NotNull final ActorEntity ActorEntity) {
        try (final Session session = factory.openSession()) {
            session.beginTransaction();
            session.save(ActorEntity);
            session.getTransaction().commit();
        }
    }

    @Override
    public ActorEntity read(@NotNull final String actor_id) {
        try (final Session session = factory.openSession()) {
            final ActorEntity result = session.get(ActorEntity.class, actor_id);
            return result != null ? result : new ActorEntity();
        }
    }

    @Override
    public void update(@NotNull final ActorEntity ActorEntity) {
        try (Session session = factory.openSession()) {
            session.beginTransaction();
            session.update(ActorEntity);
            session.getTransaction().commit();
        }
    }

    @Override
    public void delete(@NotNull final ActorEntity ActorEntity) {
        try (Session session = factory.openSession()) {
            session.beginTransaction();
            session.delete(ActorEntity);
            session.getTransaction().commit();
        }
    }
}
