package services;

import dtos.KnifeDto;

import java.util.List;

public interface KnifeService {
    void save(KnifeDto knife);
    List<KnifeDto> getAllKnives();
    List<KnifeDto> getAllWithNameContains(String pattern);
    KnifeDto findById(Long id);
    void update(KnifeDto knifeDto, Long id);
    void delete(Long id);
}
