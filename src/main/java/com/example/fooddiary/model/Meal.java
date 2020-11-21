package com.example.fooddiary.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.cache.spi.support.AbstractReadWriteAccess;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Meal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private float calories;
    private float protein;
    private float carbs;
    private float fats;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "meal")
    @JsonIgnore
    private List<ItemInMeal> itemInMealList = new ArrayList<>();

    @ManyToOne
    @JsonIgnore
    private User user_meals;

    @ManyToMany(mappedBy = "meals")
    @JsonIgnore
    private List<Cookbook> cookbooks;


}
