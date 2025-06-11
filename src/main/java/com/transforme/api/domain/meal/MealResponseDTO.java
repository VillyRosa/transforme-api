package com.transforme.api.domain.meal;

import com.transforme.api.domain.user.PublicUserDTO;

import java.time.LocalDateTime;
import java.util.UUID;

public record MealResponseDTO(
    UUID id,
    PublicUserDTO user,
    LocalDateTime mealTime,
    MealType type,
    String description,
    LocalDateTime createdAt,
    LocalDateTime updatedAt
) {
    public MealResponseDTO(Meal meal) {
        this(meal.getId(), new PublicUserDTO(meal.getUser()), meal.getMealTime(), meal.getType(), meal.getDescription(), meal.getCreatedAt(), meal.getUpdatedAt());
    }
}
