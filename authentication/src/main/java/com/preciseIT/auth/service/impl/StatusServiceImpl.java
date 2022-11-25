package com.preciseIT.auth.service.impl;

import com.preciseIT.auth.service.StatusService;
import com.preciseIT.entities.Status;
import com.preciseIT.repos.StatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StatusServiceImpl implements StatusService {
    @Autowired
    StatusRepository statusRepository;

    @Override
    public List<Status> getAll() {
        return (List<Status>) statusRepository.findAll();
    }
}
