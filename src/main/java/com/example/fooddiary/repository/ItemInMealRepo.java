package com.example.fooddiary.repository;

import com.example.fooddiary.model.ItemInMeal;
import com.example.fooddiary.model.Meal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemInMealRepo extends JpaRepository<ItemInMeal, Long> {
}
