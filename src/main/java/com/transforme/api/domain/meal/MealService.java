package com.transforme.api.domain.meal;

import com.transforme.api.domain.auth.AuthService;
import com.transforme.api.domain.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class MealService {

    @Autowired
    private MealRepository mealRepository;

    @Autowired
    private AuthService authService;

    public Page<MealResponseDTO> findAll(Pageable pageable, UUID userId) {
        if (userId != null) {
            return this.mealRepository.findByUserId(pageable, userId).map(MealResponseDTO::new);
        }

        return this.mealRepository.findAll(pageable).map(MealResponseDTO::new);
    }

    public MealResponseDTO findById(UUID id) {
        return this.mealRepository.findById(id).map(MealResponseDTO::new).orElse(null);
    }

    public MealResponseDTO create(NewMealDTO newMeal) {
        var user = this.authService.getAuthenticatedUser();

        var meal = this.mealRepository.save(new Meal(user, newMeal));
        return new MealResponseDTO(meal);
    }

    public MealResponseDTO updateById(UUID id, NewMealDTO updateMeal) {
        UUID userId = this.authService.getAuthenticatedUserId();
        var meal = this.mealRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Meal not find"));

        if (!meal.getUser().getId().equals(userId)) {
            throw new IllegalArgumentException("You can't update this meal");
        }

        meal.setMealTime(updateMeal.mealTime());
        meal.setType(updateMeal.type());
        meal.setDescription(updateMeal.description());

        return new MealResponseDTO(this.mealRepository.save(meal));
    }

    public void deleteById(UUID id) {
        UUID userId = this.authService.getAuthenticatedUserId();
        var meal = this.mealRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Meal not find"));

        if (!meal.getUser().getId().equals(userId)) {
            throw new IllegalArgumentException("You can't delete this meal");
        }

        this.mealRepository.deleteById(id);
    }

}
