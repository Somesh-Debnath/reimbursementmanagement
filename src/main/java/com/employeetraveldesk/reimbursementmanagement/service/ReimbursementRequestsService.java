package com.employeetraveldesk.reimbursementmanagement.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.employeetraveldesk.reimbursementmanagement.entity.ReimbursementRequests;
import com.employeetraveldesk.reimbursementmanagement.repository.ReimbursementRequestsRepository;

@Service
public class ReimbursementRequestsService implements IReimbursementRequestsService{

    @Autowired
    private ReimbursementRequestsRepository reimbursementRequestsRepository;

    @Override
    public ReimbursementRequests insertReimbursementRequest(ReimbursementRequests reimbursementRequest) {
        return reimbursementRequestsRepository.save(reimbursementRequest);
    }

    @Override
    public Optional<ReimbursementRequests> getReimbursementRequestsByTravelRequestId(int travelRequestId) {
        return reimbursementRequestsRepository.findById(travelRequestId);
    }

    // @Override
    // public ReimbursementRequests processReimbursementRequest(ReimbursementRequests reimbursementRequest) {
    //     return reimbursementRequestsRepository.save(reimbursementRequest);
    // }

    @Override
    public Optional<ReimbursementRequests> getReimbursementRequestById(int id) {
       return reimbursementRequestsRepository.findById(id);
    }

    /*
     * Following business rules must be implemented as part of the business service class 

The total allowed expense per day for food, laundry and local travel is as follows 

Food and water – 1000-1500 per day 

Laundry – 250-500 per day 

Local travel – upto 1000 per day 

Add a function to implement the above rules 


     */

     
        
}
