package com.employeetraveldesk.reimbursementmanagement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.employeetraveldesk.reimbursementmanagement.entity.ReimbursementTypes;
import com.employeetraveldesk.reimbursementmanagement.repository.ReimbursementTypesRepository;

@Service
public class ReimbursementTypesService implements IReimbursementTypesService {
    
    @Autowired
    private ReimbursementTypesRepository reimbursementTypesRepository;

    @Override
    public List<ReimbursementTypes> getReimbursementTypes() {
        return reimbursementTypesRepository.findAll();
    }
}
