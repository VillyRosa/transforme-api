package com.transforme.api.domain.meal;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface MealRepository extends JpaRepository<Meal, UUID> {
    Page<Meal> findByUserId(Pageable pageable, UUID userId);
}
