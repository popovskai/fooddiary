package com.example.fooddiary.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Generated;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class FoodItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private float calories;
    private float protein;
    private float carbs;
    private float fats;


    @OneToMany(cascade = CascadeType.ALL, mappedBy = "foodItem")
    @JsonIgnore
    private List<ItemInMeal> itemInMeals = new ArrayList<>();

    @ManyToOne
    @JsonIgnore
    private User user_foods;

}
