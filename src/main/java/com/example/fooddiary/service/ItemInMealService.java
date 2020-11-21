package com.example.fooddiary.service;

import com.example.fooddiary.model.FoodItem;
import com.example.fooddiary.model.ItemInMeal;
import com.example.fooddiary.model.Meal;
import com.example.fooddiary.repository.FoodItemRepo;
import com.example.fooddiary.repository.ItemInMealRepo;
import com.example.fooddiary.repository.MealRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class ItemInMealService {

    @Autowired
    private MealService mealService;
    @Autowired
    private FoodItemService foodItemService;
    @Autowired
    private ItemInMealRepo itemInMealRepo;
    @Autowired
    private MealRepo mealRepo;


    public ItemInMeal addItemToMeal(Long mealId, Long itemId, float grams) {
        ItemInMeal itemInMeal = new ItemInMeal();
        FoodItem foodItem = foodItemService.getFoodItemById(itemId);
        Meal meal = mealService.getMealById(mealId);
        itemInMeal.setMeal(meal);
        itemInMeal.setFoodItem(foodItem);
        itemInMeal.setGrams(grams);
        itemInMeal.setName(foodItem.getName());
        float calories = foodItem.getCalories()/100*grams;
        float carbs = foodItem.getCarbs()/100*grams;
        float protein = foodItem.getProtein()/100*grams;
        float fats = foodItem.getFats()/100*grams;
        meal.setCalories(meal.getCalories() + calories);
        meal.setCarbs(meal.getCarbs() + carbs);
        meal.setFats(meal.getFats() + fats);
        meal.setProtein(meal.getProtein() + protein);
        return itemInMealRepo.save(itemInMeal);
    }

    public void removeItem(Long mealId, Long itemId) {
        Meal meal = mealService.getMealById(mealId);
        ItemInMeal itemInMeal = itemInMealRepo.findById(itemId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, null));
        FoodItem foodItem = itemInMeal.getFoodItem();
        float grams = itemInMeal.getGrams();
        meal.setCalories(meal.getCalories() - foodItem.getCalories()/100*grams);
        meal.setProtein(meal.getProtein() - foodItem.getProtein()/100*grams);
        meal.setFats(meal.getFats() - foodItem.getFats()/100*grams);
        meal.setCarbs(meal.getCarbs() - foodItem.getCarbs()/100*grams);
        itemInMealRepo.delete(itemInMeal);
    }

    public ItemInMeal edit(Long itemId, float grams) {
        ItemInMeal itemInMeal = itemInMealRepo.findById(itemId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, null));
        Meal meal = itemInMeal.getMeal();
        FoodItem foodItem = itemInMeal.getFoodItem();
        float calories = foodItem.getCalories()/100*itemInMeal.getGrams();
        float carbs = foodItem.getCarbs()/100*itemInMeal.getGrams();
        float protein = foodItem.getProtein()/100*itemInMeal.getGrams();
        float fats = foodItem.getFats()/100*itemInMeal.getGrams();
        float newCalories = foodItem.getCalories()/100*grams;
        float newCarbs = foodItem.getCarbs()/100*grams;
        float newProtein = foodItem.getProtein()/100*grams;
        float newFats = foodItem.getFats()/100*grams;
        meal.setCalories(meal.getCalories() - calories + newCalories);
        meal.setCarbs(meal.getCarbs() - carbs + newCarbs);
        meal.setFats(meal.getFats() - fats + newFats);
        meal.setProtein(meal.getProtein() - protein + newProtein);
        itemInMeal.setGrams(grams);
        mealRepo.save(meal);
        return itemInMealRepo.save(itemInMeal);



    }
}
