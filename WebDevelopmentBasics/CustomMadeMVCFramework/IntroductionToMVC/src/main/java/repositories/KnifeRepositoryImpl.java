package repositories;

import models.Knife;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class KnifeRepositoryImpl implements KnifeRepository {

    private List<Knife> data;
    private Long id;
    private static KnifeRepository knifeRepository;

    public static KnifeRepository getInstance(){
        if (knifeRepository == null){
            knifeRepository = new KnifeRepositoryImpl();
        }
        return knifeRepository;
    }

    private KnifeRepositoryImpl() {
        this.data = new ArrayList<>();
        this.id = 1L;
    }

    @Override
    public void save(Knife knife) {
       knife.setId(id++);
        this.data.add(knife);
    }

    @Override
    public List<Knife> getAllKnives() {
        return Collections.unmodifiableList(this.data);
    }

    @Override
    public List<Knife> getAllWithNameContains(String pattern) {
        return this.data.stream().filter(knife -> knife.getName().toLowerCase().contains(pattern)).collect(Collectors.toList());
    }

    @Override
    public Knife getById(Long id) {
        return this.data.stream().filter(knife -> knife.getId().equals(id)).findFirst().orElse(null);
    }
}
