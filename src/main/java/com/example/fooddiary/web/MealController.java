package com.example.fooddiary.web;


import com.example.fooddiary.model.FoodItem;
import com.example.fooddiary.model.ItemInMeal;
import com.example.fooddiary.model.Meal;
import com.example.fooddiary.service.ItemInMealService;
import com.example.fooddiary.service.MealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.PermitAll;
import java.security.Principal;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/meals")
public class MealController {

    @Autowired
    private MealService mealService;
    @Autowired
    private ItemInMealService itemInMealService;

    @GetMapping("/{mealId}")
    public Meal getMeal(@PathVariable Long mealId){
        return mealService.getMealById(mealId);
    }

    @GetMapping
    public Iterable<Meal> getAllMeals(Principal principal){
        return mealService.getAll(principal.getName());
    }

    @GetMapping("/all/{mealId}")
    public Iterable<ItemInMeal> getAllItems(@PathVariable Long mealId) {return mealService.getAllItems(mealId);}

    @DeleteMapping("/{mealId}")
    public void deleteMeal(@PathVariable Long mealId){
        mealService.deleteById(mealId);
    }

    @PostMapping
    public Meal addMeal(@RequestBody Meal meal, Principal principal){
        return mealService.addMeal(meal, principal.getName());
    }

    @PostMapping("/{mealId}/{itemId}/{grams}")
    public ItemInMeal addItemToMeal(@PathVariable Long mealId, @PathVariable Long itemId, @PathVariable float grams){
        return itemInMealService.addItemToMeal(mealId, itemId, grams);
    }

    @PostMapping("/remove/{mealId}/{itemId}")
    public void removeFoodItemFromMeal(@PathVariable Long mealId, @PathVariable Long itemId){
        itemInMealService.removeItem(mealId, itemId);
    }
    @PatchMapping("/edit/{itemId}/{grams}")
    public ItemInMeal editGrams(@PathVariable Long itemId, @PathVariable float grams){
        return itemInMealService.edit(itemId, grams);
    }
}
