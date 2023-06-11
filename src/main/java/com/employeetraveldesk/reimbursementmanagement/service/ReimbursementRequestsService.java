package com.employeetraveldesk.reimbursementmanagement.service;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;

import com.employeetraveldesk.reimbursementmanagement.entity.ReimbursementRequests;
import com.employeetraveldesk.reimbursementmanagement.entity.ReimbursementTypes;
import com.employeetraveldesk.reimbursementmanagement.repository.ReimbursementRequestsRepository;
import com.employeetraveldesk.reimbursementmanagement.repository.ReimbursementTypesRepository;

@Service
public class ReimbursementRequestsService implements IReimbursementRequestsService {

    @Autowired
    private ReimbursementRequestsRepository reimbursementRequestsRepository;

    @Autowired
    private ReimbursementTypesRepository reimbursementTypesRepository;

    public ReimbursementRequestsService(ReimbursementRequestsRepository reimbursementRequestsRepository) {
        super();
        this.reimbursementRequestsRepository = reimbursementRequestsRepository;
    }

    @Override
    public ReimbursementRequests insertReimbursementRequest(ReimbursementRequests reimbursementRequest) {
        calculateConstraints(reimbursementRequest,
                reimbursementTypesRepository.findById(reimbursementRequest.getReimbursementTypeId()).get());
        return reimbursementRequestsRepository.save(reimbursementRequest);
    }

    @Override
    public Optional<ReimbursementRequests> getReimbursementRequestsByTravelRequestId(int travelRequestId) {
        return reimbursementRequestsRepository.findByTravelRequestId(travelRequestId);
    }

    @Override
    public ReimbursementRequests processReimbursementRequest(ReimbursementRequests reimbursementRequest, Integer id) {
        calculateConstraints(reimbursementRequest,
                reimbursementTypesRepository.findById(reimbursementRequest.getReimbursementTypeId()).get());
                String status = reimbursementRequest.getStatus();
        if(status.equals("Approved")) {
            reimbursementRequest.setRemarks("Reimbursement is approved");
        }
        if(status.equals("Rejected")){
            reimbursementRequest.setRemarks("Reimbursement is rejected");
        }
        if(status.equals("New")){
            reimbursementRequest.setRemarks("Reimbursement is new");
        }
        return reimbursementRequestsRepository.save(reimbursementRequest);

    }

    @Override
    public Optional<ReimbursementRequests> getReimbursementRequestById(int id) {
        return reimbursementRequestsRepository.findById(id);
    }

    @Override
    public void calculateConstraints(ReimbursementRequests reimbursementRequests,
            ReimbursementTypes reimbursementTypes) {

        if (!reimbursementRequests.getStatus().equals("Approved")
                && !reimbursementRequests.getStatus().equals("Rejected")
                && !reimbursementRequests.getStatus().equals("New")) {
            throw new IllegalArgumentException("Status must be Approved, Rejected or New");
        }
        if (reimbursementRequests.getRequestProcessedOn().isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("ProcessedOn must not be a past date");
        }
        if (reimbursementRequests.getInvoiceDate().isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("Invoice date must be a passed date");
        }
        reimbursementRequests.setRequestDate(LocalDate.now());

        if ((reimbursementRequests.getInvoiceAmount() > 1500 || reimbursementRequests.getInvoiceAmount() < 1000) &&
                (reimbursementTypes.getType().equals("Food") || reimbursementTypes.getType().equals("Water"))) {
            throw new IllegalArgumentException("Invoice amount must be in between 1000 and 1500 for Food and Water");
        }
        if ((reimbursementRequests.getInvoiceAmount() > 500 || reimbursementRequests.getInvoiceAmount() < 250)
                && reimbursementTypes.getType().equals("Laundry")) {
            throw new IllegalArgumentException("Invoice amount must be in between 250 and 500 for Laundry");
        }
        if (reimbursementRequests.getInvoiceAmount() > 1000 && reimbursementTypes.getType().equals("Local Travel")) {
            throw new IllegalArgumentException("Invoice amount must be less than 1000 for Local Travel");
        }
        // if(!reimbursementRequests.getDocumentURL().endsWith(".pdf") &&
        // reimbursementRequests.getDocumentSize() > 256)
        // {
        // throw new IllegalArgumentException("Document must be a pdf and size must be
        // less than 256 KB");
        // }
        // if (reimbursementRequests.getInvoiceDate().compareTo(reimbursementRequests.getTravelStartDate()) <= 0
        //         || reimbursementRequests.getInvoiceDate().compareTo(reimbursementRequests.getTravelEndDate()) >= 0) {
        //     throw new IllegalArgumentException("Invoice date must be with in the from and to date of the travel");
        // }
        if (reimbursementRequests.getStatus().equals("Rejected") || reimbursementRequests.getRemarks().isEmpty()) {
            reimbursementRequests.setRemarks("Reimbursement is rejected");
        }
    }

}
