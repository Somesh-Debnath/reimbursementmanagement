package com.employeetraveldesk.reimbursementmanagement.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.employeetraveldesk.reimbursementmanagement.entity.ReimbursementRequests;

public interface ReimbursementRequestsRepository extends JpaRepository<ReimbursementRequests, Integer>{
    @Query("SELECT r FROM ReimbursementRequests r WHERE r.TravelRequestId =?1")
    Optional<ReimbursementRequests> findByTravelRequestId(int travelRequestid);
}
