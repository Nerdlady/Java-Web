package repositories;

import models.Knife;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.Collections;
import java.util.List;

@Stateless
@Local(KnifeRepository.class)
public class KnifeRepositoryImpl implements KnifeRepository {

    @PersistenceContext(unitName = "db")
    private EntityManager entityManager;

    @Override
    public void save(Knife knife) {
        this.entityManager.persist(knife);
    }

    @Override
    public List<Knife> getAllKnives() {
        List<Knife> knives = this.entityManager.createQuery("SELECT k FROM knives AS k").getResultList();
        return Collections.unmodifiableList(knives);
    }

    @Override
    public List<Knife> getAllWithNameContains(String pattern) {
        Query query = this.entityManager.createQuery("SELECT k FROM knives AS k WHERE lower(k.name) LIKE :pattern");
        query.setParameter("pattern","%" + pattern +"%");
        List<Knife> knives = query.getResultList();
        return knives;
    }

    @Override
    public Knife getById(Long id) {
        Query query = this.entityManager.createQuery("SELECT k FROM knives AS k WHERE k.id = :id");
        query.setParameter("id",id);
        return (Knife) query.getSingleResult();
    }

    @Override
    public void delete(Knife knife) {
        this.entityManager.remove(knife);
    }
}
