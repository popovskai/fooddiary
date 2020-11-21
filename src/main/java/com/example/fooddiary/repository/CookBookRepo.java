package com.example.fooddiary.repository;


import com.example.fooddiary.model.Cookbook;
import com.example.fooddiary.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CookBookRepo extends JpaRepository<Cookbook, Long> {
    //List<Cookbook> findByUser_ownerNot(User user);
}
