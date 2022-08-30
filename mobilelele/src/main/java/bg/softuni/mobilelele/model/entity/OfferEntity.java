package bg.softuni.mobilelele.model.entity;

import bg.softuni.mobilelele.model.enums.EngineEnum;
import bg.softuni.mobilelele.model.enums.TransmissionEnum;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "offers")
public class OfferEntity extends BaseEntity {

    @Column
    private String description;

    @Enumerated(EnumType.STRING)
    private EngineEnum engine;

    @Column
    private String imageUrl;
    
    @Column
    private Double mileage;

    @Column
    private BigDecimal price;

    @Enumerated(EnumType.STRING)
    private TransmissionEnum transmission;

    @Column
    private Integer year;

//    private Model model;
//
//    private Seller seller;
}
