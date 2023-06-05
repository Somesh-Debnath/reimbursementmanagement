package com.employeetraveldesk.reimbursementmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.employeetraveldesk.reimbursementmanagement.entity.ReimbursementRequests;

public interface ReimbursementRequestsRepository extends JpaRepository<ReimbursementRequests, Integer>{
    
}
