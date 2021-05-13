package com.nayoung.app.repository;

import com.nayoung.app.domain.Reserve;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReserveRepository extends JpaRepository<Reserve, Long> {
    Reserve findByName(final String name);
}
