package com.example.fooddiary.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ItemInMeal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private float grams;
    private String name;

    @ManyToOne
    @JsonIgnore
    private FoodItem foodItem;
    @ManyToOne
    @JsonIgnore
    private Meal meal;
}
