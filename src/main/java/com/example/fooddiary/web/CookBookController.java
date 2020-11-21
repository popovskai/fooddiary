package com.example.fooddiary.web;

import com.example.fooddiary.model.Cookbook;
import com.example.fooddiary.model.Meal;
import com.example.fooddiary.model.Type;
import com.example.fooddiary.service.CookBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Arrays;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/cookbooks")
public class CookBookController {

    @Autowired
    private CookBookService cookBookService;

    @GetMapping
    public Iterable<Cookbook> getAllCookbooks(Principal principal){

        return cookBookService.getAll(principal);
    }

    @GetMapping("/types")
    public Iterable<Type> getTypes(){
        return Arrays.asList(Type.values());
    }

    @GetMapping("/user")
    public Iterable<Cookbook> getAllByUser(Principal principal){
        return  cookBookService.getAllByUser(principal.getName());
    }

    @GetMapping("/meals/{cookBookId}")
    public Iterable<Meal> getAllMeals(@PathVariable Long cookBookId){
        return cookBookService.getAllMeals(cookBookId);
    }
    @PostMapping
    public Cookbook createCookbook(Principal principal, @RequestBody Cookbook cookbook){
        return cookBookService.createCookbook(cookbook, principal.getName());
    }
    @PostMapping("/{cookBookId}/{mealId}")
    public Cookbook addMealToCookbook(@PathVariable Long cookBookId, @PathVariable Long mealId){
        return cookBookService.addMeal(cookBookId, mealId);
    }
    @DeleteMapping("/delete/{cookBookId}")
    public void deleteCookBook(@PathVariable Long cookBookId){
        cookBookService.deleteCookBook(cookBookId);
    }
}
