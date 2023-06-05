package com.employeetraveldesk.reimbursementmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.employeetraveldesk.reimbursementmanagement.entity.ReimbursementTypes;

public interface ReimbursementTypesRepository extends JpaRepository<ReimbursementTypes, Integer> {
    
}
