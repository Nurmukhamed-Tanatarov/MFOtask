package com.micro.pe.mfo.service;

import com.micro.pe.mfo.entity.Limit;
import com.micro.pe.mfo.repository.LimitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LimitService {

    @Autowired
    private LimitRepository limitRepository;

    public Limit getLatestLimitForCategory(String category) {
        return limitRepository.findFirstByCategoryOrderByStartDateDesc(category);
    }

}
