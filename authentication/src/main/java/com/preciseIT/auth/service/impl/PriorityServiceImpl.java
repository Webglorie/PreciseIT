package com.preciseIT.auth.service.impl;

import com.preciseIT.auth.service.PriorityService;
import com.preciseIT.entities.Priority;
import com.preciseIT.repos.PriorityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PriorityServiceImpl implements PriorityService {

    @Autowired
    PriorityRepository priorityRepository;


    @Override
    public Priority getPriorityById(int id) {
        return priorityRepository.getById(id);
    }
}
