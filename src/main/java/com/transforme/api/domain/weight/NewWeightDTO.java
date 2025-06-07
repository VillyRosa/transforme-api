package com.transforme.api.domain.weight;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record NewWeightDTO(
    BigDecimal weightKg,
    LocalDateTime measuredAt,
    String note
) {
}
