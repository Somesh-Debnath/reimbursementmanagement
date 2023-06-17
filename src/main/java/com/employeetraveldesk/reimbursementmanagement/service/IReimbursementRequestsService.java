package com.employeetraveldesk.reimbursementmanagement.service;

import java.util.Optional;

import com.employeetraveldesk.reimbursementmanagement.entity.ReimbursementRequests;
import com.employeetraveldesk.reimbursementmanagement.entity.ReimbursementTypes;

public interface IReimbursementRequestsService {

    ReimbursementRequests insertReimbursementRequest(ReimbursementRequests reimbursementRequest);

    Optional<ReimbursementRequests> getReimbursementRequestsByTravelRequestId(int travelRequestId);

    ReimbursementRequests processReimbursementRequest(ReimbursementRequests reimbursementRequest, Integer id);

    void calculateConstraints(ReimbursementRequests reimbursementRequests, ReimbursementTypes reimbursementTypes);

    Optional<ReimbursementRequests> getReimbursementRequestById(int id);

}
