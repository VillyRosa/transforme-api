package com.transforme.api.domain.meal;

import com.transforme.api.domain.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "meals")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Meal {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    private LocalDateTime mealTime;

    private MealType type;

    private String description;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    public Meal(User user, NewMealDTO newMeal) {
        this.user = user;
        this.mealTime = newMeal.mealTime();
        this.type = newMeal.type();
        this.description = newMeal.description();
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

}
