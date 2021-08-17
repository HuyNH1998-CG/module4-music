package Big.Service;

import Big.Model.Category;
import Big.Model.Music;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@Service
public class MusicService implements Big.Service.CustomerS {
    private static SessionFactory sessionFactory;
    private static EntityManager entityManager;

    static {
        try {
            sessionFactory = new Configuration()
                    .configure("hibernate.conf.xml")
                    .buildSessionFactory();
            entityManager = sessionFactory.createEntityManager();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }

    public List<Music> findAll() {
        String queryStr = "select c from Music as c";
        TypedQuery<Music> query = entityManager.createQuery(queryStr, Music.class);
        return query.getResultList();
    }

    public List<Category> findAllCat() {
        String queryStr = "select c from Category as c";
        TypedQuery<Category> query = entityManager.createQuery(queryStr, Category.class);
        return query.getResultList();
    }

    public Music save(Music music) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            Music origin = new Music();
            origin.setName(music.getName());
            origin.setSinger(music.getSinger());
            origin.setType(music.getType());
            origin.setLink(music.getLink());
            session.save(origin);
            transaction.commit();
            return origin;
        } catch (Exception e){
            e.printStackTrace();
        }
        finally {
            if (session != null){
                session.close();
            }
        }
        return null;
    }

    public Music findById(int id) {
        String queryStr = "select c from Music as c where c.id = :id";
        TypedQuery<Music> query = entityManager.createQuery(queryStr, Music.class);
        query.setParameter("id", id);
        return query.getSingleResult();
    }

    public Music update(int id, Music music) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            Music origin = findById(id);
            origin.setName(music.getName());
            origin.setSinger(music.getSinger());
            origin.setType(music.getType());
            origin.setLink(music.getLink());
            session.update(music);
            transaction.commit();
            return origin;
        } catch (Exception e){
            e.printStackTrace();
        }
        finally {
            if (session != null){
                session.close();
            }
        }
        return null;
    }

    public void remove(int id) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            Music origin = findById(id);
            session.delete(origin);
            transaction.commit();
        } catch (Exception e){
            e.printStackTrace();
        }
        finally {
            if (session != null){
                session.close();
            }
        }
    }
}
