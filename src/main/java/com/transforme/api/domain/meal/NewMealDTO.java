package com.transforme.api.domain.meal;

import java.time.LocalDateTime;

public record NewMealDTO(
    LocalDateTime mealTime,
    MealType type,
    String description
) {
}
