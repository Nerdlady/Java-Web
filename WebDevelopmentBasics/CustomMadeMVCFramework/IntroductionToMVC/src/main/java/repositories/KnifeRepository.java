package repositories;


import models.Knife;

import java.util.List;

public interface KnifeRepository {
    void save(Knife knife);

    List<Knife> getAllKnives();

    List<Knife> getAllWithNameContains(String pattern);

    Knife getById(Long id);

    void delete(Knife knife);
}
