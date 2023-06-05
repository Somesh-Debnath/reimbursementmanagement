package com.employeetraveldesk.reimbursementmanagement.service;

import java.util.List;
import java.util.Optional;

import com.employeetraveldesk.reimbursementmanagement.entity.ReimbursementRequests;
import com.employeetraveldesk.reimbursementmanagement.entity.ReimbursementTypes;

public interface IReimbursementRequestsService {
    /*
     * Return the available Reimbursement types
     * 
     * Insert a new Reimbursement
     * 
     * Return all Reimbursements by travel request id
     * 
     * Process the reimbursement
     * 
     * Return a reimbursement by id
     * 
     * 
     */
    //public List<ReimbursementTypes> getReimbursementTypes();

    public ReimbursementRequests insertReimbursementRequest(ReimbursementRequests reimbursementRequest);

    public Optional<ReimbursementRequests> getReimbursementRequestsByTravelRequestId(int travelRequestId);

    //public ReimbursementRequests processReimbursementRequest(ReimbursementRequests reimbursementRequest);

    public Optional<ReimbursementRequests> getReimbursementRequestById(int id);

}
