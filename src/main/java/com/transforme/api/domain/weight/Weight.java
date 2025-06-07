package com.transforme.api.domain.weight;

import com.transforme.api.domain.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Table(name = "weights")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Weight {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    private BigDecimal weightKg;

    private LocalDateTime measuredAt;

    private String note;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    public Weight(User user, NewWeightDTO newWeight) {
        this.user = user;
        this.weightKg = newWeight.weightKg();
        this.measuredAt = newWeight.measuredAt();
        this.note = newWeight.note();
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

}
