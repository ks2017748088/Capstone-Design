package com.nayoung.app.repository;

import com.nayoung.app.domain.Trainer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrainerRepository extends JpaRepository<Trainer, Long> {

}
