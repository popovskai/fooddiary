package com.example.fooddiary.service;

import com.example.fooddiary.model.FoodItem;
import com.example.fooddiary.model.ItemInMeal;
import com.example.fooddiary.model.Meal;
import com.example.fooddiary.model.User;
import com.example.fooddiary.repository.MealRepo;
import com.example.fooddiary.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class MealService {

    @Autowired
    private MealRepo mealRepo;
    @Autowired
    private UserRepo userRepo;

    public Meal getMealById(Long mealId) {
        return mealRepo.findById(mealId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, null));
    }

    public Iterable<Meal> getAll(String name) {
        User user = userRepo.findByUsername(name);
        List<Meal> meals = user.getMeals();
        return meals;
    }

    public void deleteById(Long mealId) {
        mealRepo.deleteById(mealId);
    }

    public Meal addMeal(Meal meal, String name) {
        User user = userRepo.findByUsername(name);
        meal.setUser_meals(user);
        Meal saved = mealRepo.save(meal);
        user.getMeals().add(saved);
        userRepo.save(user);
        return saved;
    }

    public Iterable<ItemInMeal> getAllItems(Long mealId) {
        Meal meal = getMealById(mealId);
        List<ItemInMeal> items = meal.getItemInMealList();
        /*List<FoodItem> foodItems = null;
        for(ItemInMeal item : items){
            foodItems.add(item.getFoodItem());
        }*/
        return items;
    }
}

