package bg.softuni.mobilelele.model.entity;

import bg.softuni.mobilelele.model.enums.CategoryEnum;

import javax.persistence.*;

@Entity
@Table(name = "models")
public class ModelEntity extends BaseEntity {

    @Column
    private String name;

    @Enumerated(EnumType.STRING)
    private CategoryEnum category;

    @Column
    private String imageURL;

    @Column
    private int startYear;

    @Column
    private Long endYear;
    
    
}
