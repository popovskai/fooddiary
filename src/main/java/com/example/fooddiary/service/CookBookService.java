package com.example.fooddiary.service;

import com.example.fooddiary.model.Cookbook;
import com.example.fooddiary.model.Meal;
import com.example.fooddiary.model.User;
import com.example.fooddiary.repository.CookBookRepo;
import com.example.fooddiary.repository.MealRepo;
import com.example.fooddiary.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Service
public class CookBookService {

    @Autowired
    private CookBookRepo cookBookRepo;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private MealRepo mealRepo;

    public Iterable<Cookbook> getAll(Principal principal) {
        User user = userRepo.findByUsername(principal.getName());
        List<Cookbook> cookbooksFound = new ArrayList<>();
        List<Cookbook> cookbooks = cookBookRepo.findAll();
        for(Cookbook cookbook : cookbooks){
            if(cookbook.getUser_owner()!= user){
                cookbooksFound.add(cookbook);
            }
        }
        return cookbooksFound;
    }

    public Cookbook createCookbook(Cookbook cookbook, String name) {
        User user = userRepo.findByUsername(name);
        cookbook.setUser_owner(user);
        Cookbook saved = cookBookRepo.save(cookbook);
        user.getCookbooks().add(saved);
        userRepo.save(user);
        return saved;
    }

    public Cookbook addMeal(Long id, Long mealId) {
        Cookbook cookbook = cookBookRepo.findById(id).orElse(null);
        Meal meal = mealRepo.findById(mealId).orElse(null);
        cookbook.getMeals().add(meal);
        meal.getCookbooks().add(cookbook);
        mealRepo.save(meal);
        return cookBookRepo.save(cookbook);
    }

    public Iterable<Cookbook> getAllByUser(String name) {
        User user = userRepo.findByUsername(name);
        List<Cookbook> cookbooks = user.getCookbooks();
        return cookbooks;
    }

    public Iterable<Meal> getAllMeals(Long cookBookId) {
        Cookbook cookbook = cookBookRepo.findById(cookBookId).orElse(null);
        return cookbook.getMeals();
    }

    public void deleteCookBook(Long cookBookId) {
        cookBookRepo.deleteById(cookBookId);
    }
}
