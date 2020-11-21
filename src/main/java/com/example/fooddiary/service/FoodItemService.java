package com.example.fooddiary.service;

import com.example.fooddiary.model.FoodItem;
import com.example.fooddiary.model.User;
import com.example.fooddiary.repository.FoodItemRepo;
import com.example.fooddiary.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class FoodItemService {

    @Autowired
    private FoodItemRepo foodItemRepo;
    @Autowired
    private UserRepo userRepo;

    public FoodItem getFoodItemById(Long foodItemId) {
        return foodItemRepo.findById(foodItemId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, null));
    }

    public FoodItem addFoodItem(FoodItem foodItem, String name) {
        User user = userRepo.findByUsername(name);
        FoodItem saved = foodItemRepo.save(foodItem);
        saved.setUser_foods(user);
        user.getFoodItems().add(saved);
        userRepo.save(user);
        return saved;
    }

    public void deleteById(Long foodItemId) {

        foodItemRepo.deleteById(foodItemId);
    }

    public Iterable<FoodItem> getAll(String name) {
        User user = userRepo.findByUsername(name);
        List<FoodItem> foodItems = user.getFoodItems();
        return foodItems;
    }

    public FoodItem editFoodItem(FoodItem foodItem) {
        FoodItem f = getFoodItemById(foodItem.getId());
        f.setName(foodItem.getName());
        f.setCalories(foodItem.getCalories());
        f.setCarbs(foodItem.getCarbs());
        f.setFats(foodItem.getFats());
        f.setProtein(foodItem.getProtein());
        return foodItemRepo.save(f);
    }
}
