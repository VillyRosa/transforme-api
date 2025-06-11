package com.transforme.api.controller;

import com.transforme.api.domain.meal.MealResponseDTO;
import com.transforme.api.domain.meal.MealService;
import com.transforme.api.domain.meal.NewMealDTO;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/meals")
public class MealController {

    @Autowired
    private MealService mealService;

    @GetMapping
    public ResponseEntity<Page<MealResponseDTO>> getAll(Pageable pageable, @RequestParam(required = false) UUID userId) {
        return ResponseEntity.ok().body(this.mealService.findAll(pageable, userId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<MealResponseDTO> getById(@PathVariable UUID id) {
        return ResponseEntity.ok().body(this.mealService.findById(id));
    }

    @PostMapping
    public ResponseEntity<MealResponseDTO> create(@RequestBody NewMealDTO newMeal) {
        return ResponseEntity.ok().body(this.mealService.create(newMeal));
    }

    @PutMapping("/{id}")
    public ResponseEntity<MealResponseDTO> updateById(@PathVariable UUID id, @RequestBody NewMealDTO updateMeal) {
        return ResponseEntity.ok().body(this.mealService.updateById(id, updateMeal));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable UUID id) {
        this.mealService.deleteById(id);
        return ResponseEntity.ok().build();
    }

}
