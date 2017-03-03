package pizzaForum.repositories;

import pizzaForum.entities.Category;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Stateless
@Local(CategoryRepository.class)
public class CategoryRepositoryImpl implements CategoryRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void save(Category category) {
        this.entityManager.persist(category);
    }

    @Override
    public List<Category> getAllCategories() {
        Query query = this.entityManager.createQuery("SELECT c FROM Category AS c");
        return query.getResultList();
    }

    @Override
    public Category getById(Long id) {
        Query query = this.entityManager.createQuery("SELECT c FROM Category AS c WHERE c.id = :id");
        query.setParameter("id", id);
        return (Category) query.getResultList().stream().findFirst().orElse(null);
    }

    @Override
    public void update(Category category) {
        this.entityManager.merge(category);
    }

    @Override
    public void delete(Long id) {
        Query query = this.entityManager.createQuery("DELETE FROM Category AS c WHERE c.id = :id");
        query.setParameter("id",id);
        query.executeUpdate();
    }

    @Override
    public Category findByName(String name) {
        Query query = this.entityManager.createQuery("SELECT c FROM Category AS c WHERE c.name = :name");
        query.setParameter("name",name);
        return (Category) query.getResultList().stream().findFirst().orElse(null);
    }
}
