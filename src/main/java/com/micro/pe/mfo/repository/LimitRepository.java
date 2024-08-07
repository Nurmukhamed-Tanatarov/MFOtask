package com.micro.pe.mfo.repository;

import org.hibernate.query.spi.Limit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LimitRepository extends JpaRepository<com.micro.pe.mfo.entity.Limit, Long> {
    Limit findFirstByCategoryOrderByStartDateDesc(String category);
}
