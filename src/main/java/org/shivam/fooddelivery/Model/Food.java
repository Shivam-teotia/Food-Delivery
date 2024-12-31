package org.shivam.fooddelivery.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Food {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private Long price;

    @ManyToOne
    private Category foodCategory;


    @ElementCollection
    @Column(length = 1000)
    private List<String> images;

    private boolean available;

    //    @JsonIgnore
    @ManyToOne
    private Restaurant restaurant;

    private boolean isVegetarian;
    private boolean isSeasonal;

    @ManyToMany
    private List<IngredientsItem> ingredients=new ArrayList<>();

    @Temporal(TemporalType.TIMESTAMP)
    private Date creationDate;
}
