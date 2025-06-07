package com.transforme.api.domain.weight;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface WeightRepository extends JpaRepository<Weight, UUID> {
    Page<Weight> findByUserId(UUID userId, Pageable pageable);
}
