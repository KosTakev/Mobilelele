package bg.softuni.mobilelele.service;

import bg.softuni.mobilelele.model.dto.AddOfferDto;
import bg.softuni.mobilelele.model.dto.BrandDto;
import bg.softuni.mobilelele.repository.OfferRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OfferService {

    private OfferRepository offerRepository;

    public OfferService(OfferRepository offerRepository) {
        this.offerRepository = offerRepository;
    }

    public void addOffer(AddOfferDto addOfferDto) {
        //TODO
    }
}
