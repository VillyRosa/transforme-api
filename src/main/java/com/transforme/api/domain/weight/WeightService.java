package com.transforme.api.domain.weight;

import com.transforme.api.domain.auth.AuthService;
import com.transforme.api.domain.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class WeightService {

    @Autowired
    private WeightRepository weightRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthService authService;

    public Page<WeightResponseDTO> findAll(Pageable pageable, UUID userId) {
        if (userId != null) {
            return this.weightRepository.findByUserId(userId, pageable).map(WeightResponseDTO::new);
        }

        return this.weightRepository.findAll(pageable).map(WeightResponseDTO::new);
    }

    public WeightResponseDTO create(NewWeightDTO newWeight) {
        UUID userId = this.authService.getAuthenticatedUserId();
        var user = this.userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("User not find"));

        var weight = this.weightRepository.save(new Weight(user, newWeight));
        return new WeightResponseDTO(weight);
    }

    public WeightResponseDTO findById(UUID id) {
        return this.weightRepository.findById(id).map(WeightResponseDTO::new).orElse(null);
    }

    public WeightResponseDTO update(UUID id, NewWeightDTO updateWeight) {
        UUID userId = this.authService.getAuthenticatedUserId();
        var weight = this.weightRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Weight not find"));

        if (!weight.getUser().getId().equals(userId)) {
            throw new IllegalArgumentException("You can't delete this weight");
        }

        weight.setWeightKg(updateWeight.weightKg());
        weight.setMeasuredAt(updateWeight.measuredAt());
        weight.setNote(updateWeight.note());

        return new WeightResponseDTO(this.weightRepository.save(weight));
    }

    public void deleteById(UUID id) {
        UUID userId = this.authService.getAuthenticatedUserId();
        var weight = this.weightRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Weight not find"));

        if (!weight.getUser().getId().equals(userId)) {
            throw new IllegalArgumentException("You can't delete this weight");
        }

        this.weightRepository.deleteById(id);
    }

}
