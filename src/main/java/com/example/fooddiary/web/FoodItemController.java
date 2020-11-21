package com.example.fooddiary.web;

import com.example.fooddiary.model.FoodItem;
import com.example.fooddiary.service.FoodItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/food")
public class FoodItemController {

    @Autowired
    private FoodItemService foodItemService;

    @GetMapping("/{foodItemId}")
    public FoodItem getFoodItem(@PathVariable Long foodItemId){
        FoodItem foodItem = foodItemService.getFoodItemById(foodItemId);
        return foodItem;
    }

    @GetMapping
    public Iterable<FoodItem> getAllFoodItems(Principal principal){
        return foodItemService.getAll(principal.getName());
    }

    @PostMapping
    public FoodItem addFoodItem(@RequestBody FoodItem foodItem, Principal principal){
        return foodItemService.addFoodItem(foodItem, principal.getName());
    }

    @DeleteMapping("/{foodItemId}")
    public void deleteFoodItem(@PathVariable Long foodItemId){
        foodItemService.deleteById(foodItemId);
    }

    @PatchMapping("/edit")
    public FoodItem editFoodItem(@RequestBody FoodItem foodItem){
        return foodItemService.editFoodItem(foodItem);
    }
}
