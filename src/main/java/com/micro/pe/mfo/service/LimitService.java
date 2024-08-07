package com.micro.pe.mfo.service;

import com.micro.pe.mfo.repository.LimitRepository;
import org.hibernate.query.spi.Limit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LimitService {
    @Autowired
    private LimitRepository limitRepository;

    public Limit getLatestLimitForCategory(String category) {
        // Логика для получения последнего установленного лимита для данной категории
        return limitRepository.findFirstByCategoryOrderByStartDateDesc(category);
    }
}
