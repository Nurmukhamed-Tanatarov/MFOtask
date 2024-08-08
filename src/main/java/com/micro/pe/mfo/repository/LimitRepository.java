package com.micro.pe.mfo.repository;

import com.micro.pe.mfo.entity.Limit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LimitRepository extends JpaRepository<Limit, Long> {
    Limit findFirstByCategoryOrderByStartDateDesc(String category);
}
