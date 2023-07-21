package com.competa.competademo.repository;

import com.competa.competademo.models.Competa;
import org.springframework.data.repository.CrudRepository;

public interface CompetaRepository extends CrudRepository< Competa, Long> {
}
