package com.nayoung.app.repository;

import com.nayoung.app.domain.Reserve;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReserveRepository extends JpaRepository<Reserve, Long> {
}
