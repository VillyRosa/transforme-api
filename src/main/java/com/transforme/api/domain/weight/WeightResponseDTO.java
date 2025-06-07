package com.transforme.api.domain.weight;

import com.transforme.api.domain.user.PublicUserDTO;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record WeightResponseDTO(
    UUID id,
    PublicUserDTO user,
    BigDecimal weightKg,
    LocalDateTime measuredAt,
    String note,
    LocalDateTime createdAt,
    LocalDateTime updatedAt
) {
    public WeightResponseDTO(Weight weight) {
        this(weight.getId(), new PublicUserDTO(weight.getUser()), weight.getWeightKg(), weight.getMeasuredAt(), weight.getNote(), weight.getCreatedAt(), weight.getUpdatedAt());
    }
}
