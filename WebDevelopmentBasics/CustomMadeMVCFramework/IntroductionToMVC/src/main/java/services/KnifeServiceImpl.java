package services;

import dtos.KnifeDto;
import models.Knife;
import org.modelmapper.ModelMapper;
import repositories.KnifeRepository;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class KnifeServiceImpl implements KnifeService {
    @Inject
    private KnifeRepository knifeRepository;

    @Override
    public void save(KnifeDto knifeDto) {
        ModelMapper modelMapper = new ModelMapper();
        Knife knife = modelMapper.map(knifeDto,Knife.class);
        this.knifeRepository.save(knife);
    }

    @Override
    public List<KnifeDto> getAllKnives() {
        List<KnifeDto> knifeDtos = new ArrayList<>();
        List<Knife> knives = this.knifeRepository.getAllKnives();
        ModelMapper modelMapper = new ModelMapper();
        for (Knife knife : knives) {
            KnifeDto knifeDto = modelMapper.map(knife,KnifeDto.class);
            knifeDtos.add(knifeDto);
        }
        return knifeDtos;
    }

    @Override
    public List<KnifeDto> getAllWithNameContains(String pattern) {
        List<Knife> knives = this.knifeRepository.getAllWithNameContains(pattern);
        List<KnifeDto> knifeDtos = new ArrayList<>();
        ModelMapper modelMapper = new ModelMapper();
        for (Knife knife : knives) {
            KnifeDto knifeDto = modelMapper.map(knife,KnifeDto.class);
            knifeDtos.add(knifeDto);
        }
        return knifeDtos;
    }

    @Override
    public KnifeDto findById(Long id) {
        Knife knife = this.knifeRepository.getById(id);
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(knife,KnifeDto.class);
    }

    @Override
    public void update(KnifeDto knifeDto, Long id) {
        Knife knife = this.knifeRepository.getById(id);
        knife.setName(knifeDto.getName());
        knife.setPrice(knifeDto.getPrice());
        knife.setImageURL(knifeDto.getImageURL());
        this.knifeRepository.save(knife);
    }

    @Override
    public void delete(Long id) {
        Knife knife = this.knifeRepository.getById(id);
        this.knifeRepository.delete(knife);
    }
}
