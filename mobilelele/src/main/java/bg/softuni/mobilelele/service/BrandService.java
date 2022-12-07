package bg.softuni.mobilelele.service;

import bg.softuni.mobilelele.model.dto.BrandDto;
import bg.softuni.mobilelele.model.dto.ModelDto;
import bg.softuni.mobilelele.model.entity.BrandEntity;
import bg.softuni.mobilelele.model.entity.ModelEntity;
import bg.softuni.mobilelele.repository.BrandRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BrandService {

    private BrandRepository brandRepository;

    public BrandService(BrandRepository brandRepository) {
        this.brandRepository = brandRepository;
    }

    public List<BrandDto> getAllBrands() {
        return brandRepository
                .findAll()
                .stream()
                .map(this::mapBrand).collect(Collectors.toList());
    }

    private BrandDto mapBrand(BrandEntity brandEntity) {
        List<ModelDto> models = brandEntity
                .getModels()
                .stream()
                .map(this::mapModel)
                .collect(Collectors.toList());

        return new BrandDto()
                .setModels(models)
                .setName(brandEntity.getName());
    }

    private ModelDto mapModel(ModelEntity modelEntity) {
        return new ModelDto()
                .setId(modelEntity.getId())
                .setName(modelEntity.getName());
    }
}
