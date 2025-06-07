package com.transforme.api.controller;

import com.transforme.api.domain.weight.NewWeightDTO;
import com.transforme.api.domain.weight.WeightResponseDTO;
import com.transforme.api.domain.weight.WeightService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/weights")
public class WeightController {

    @Autowired
    private WeightService weightService;

    @GetMapping
    public ResponseEntity<Page<WeightResponseDTO>> getAll(Pageable pageable, @RequestParam(required = false) UUID userId) {
        return ResponseEntity.ok().body(this.weightService.findAll(pageable, userId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<WeightResponseDTO> getById(@PathVariable UUID id) {
        return ResponseEntity.ok().body(this.weightService.findById(id));
    }

    @PostMapping
    public ResponseEntity<WeightResponseDTO> create(@RequestBody NewWeightDTO newWeight) {
        return ResponseEntity.ok().body(this.weightService.create(newWeight));
    }

    @PutMapping("/{id}")
    public ResponseEntity<WeightResponseDTO> update(@PathVariable UUID id, @RequestBody NewWeightDTO updateWeight) {
        return ResponseEntity.ok().body(this.weightService.update(id, updateWeight));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable UUID id) {
        this.weightService.deleteById(id);
        return ResponseEntity.ok().build();
    }

}
