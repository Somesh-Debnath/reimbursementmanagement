package com.employeetraveldesk.reimbursementmanagement.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.employeetraveldesk.reimbursementmanagement.entity.ReimbursementRequests;
import com.employeetraveldesk.reimbursementmanagement.entity.ReimbursementTypes;
import com.employeetraveldesk.reimbursementmanagement.repository.ReimbursementRequestsRepository;
import com.employeetraveldesk.reimbursementmanagement.repository.ReimbursementTypesRepository;

public class ReimbursementRequestsServiceTest {
    @Mock
    private ReimbursementRequestsRepository reimbursementRequestsRepository;

    @Mock
    private ReimbursementTypesRepository reimbursementTypesRepository;

    @InjectMocks
    private ReimbursementRequestsService reimbursementRequestsService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testInsertReimbursementRequest() {
        ReimbursementRequests reimbursementRequest = new ReimbursementRequests();
        reimbursementRequest.setReimbursementTypeId(1);
        reimbursementRequest.setStatus("New");
        reimbursementRequest.setInvoiceDate(LocalDate.now());
        reimbursementRequest.setInvoiceAmount(1000);

        ReimbursementTypes reimbursementType = new ReimbursementTypes();
        reimbursementType.setType("Food");

        when(reimbursementTypesRepository.findById(reimbursementRequest.getReimbursementTypeId()))
                .thenReturn(Optional.of(reimbursementType));
        when(reimbursementRequestsRepository.save(any(ReimbursementRequests.class))).thenReturn(reimbursementRequest);

        ReimbursementRequests insertedRequest = reimbursementRequestsService
                .insertReimbursementRequest(reimbursementRequest);

        assertNotNull(insertedRequest);
        assertEquals("New", insertedRequest.getStatus());
        assertNotNull(insertedRequest.getRequestDate());

        verify(reimbursementTypesRepository, times(1)).findById(reimbursementRequest.getReimbursementTypeId());
        verify(reimbursementRequestsRepository, times(1)).save(reimbursementRequest);
    }

    @Test
    public void testProcessReimbursementRequest() {
        ReimbursementRequests reimbursementRequest = new ReimbursementRequests();
        reimbursementRequest.setReimbursementTypeId(1);
        reimbursementRequest.setStatus("Approved");
        reimbursementRequest.setInvoiceDate(LocalDate.now());
        reimbursementRequest.setInvoiceAmount(1000);

        ReimbursementTypes reimbursementType = new ReimbursementTypes();
        reimbursementType.setType("Food");

        when(reimbursementTypesRepository.findById(reimbursementRequest.getReimbursementTypeId()))
                .thenReturn(Optional.of(reimbursementType));
        when(reimbursementRequestsRepository.save(any(ReimbursementRequests.class))).thenReturn(reimbursementRequest);

        ReimbursementRequests processedRequest = reimbursementRequestsService
                .processReimbursementRequest(reimbursementRequest, 1);

        assertNotNull(processedRequest);
        assertEquals("Approved", processedRequest.getStatus());
        assertEquals("Reimbursement is approved", processedRequest.getRemarks());

        verify(reimbursementTypesRepository, times(1)).findById(reimbursementRequest.getReimbursementTypeId());
        verify(reimbursementRequestsRepository, times(1)).save(reimbursementRequest);
    }

    @Test
    public void testGetReimbursementRequestById() {
        int requestId = 1;
        ReimbursementRequests reimbursementRequest = new ReimbursementRequests();
        reimbursementRequest.setId(requestId);
        reimbursementRequest.setStatus("New");

        when(reimbursementRequestsRepository.findById(requestId)).thenReturn(Optional.of(reimbursementRequest));

        Optional<ReimbursementRequests> retrievedRequest = reimbursementRequestsService
                .getReimbursementRequestById(requestId);

        assertTrue(retrievedRequest.isPresent());
        assertEquals(requestId, retrievedRequest.get().getId());
        assertEquals("New", retrievedRequest.get().getStatus());

        verify(reimbursementRequestsRepository, times(1)).findById(requestId);
    }

     @Test
    public void testGetReimbursementRequestsByTravelRequestId() {
        int travelRequestId = 1;
        ReimbursementRequests reimbursementRequest = new ReimbursementRequests();
        reimbursementRequest.setId(1);
        reimbursementRequest.setTravelRequestId(travelRequestId);

        when(reimbursementRequestsRepository.findByTravelRequestId(travelRequestId))
                .thenReturn(Optional.of(reimbursementRequest));

        Optional<ReimbursementRequests> retrievedRequest = reimbursementRequestsService
                .getReimbursementRequestsByTravelRequestId(travelRequestId);

        assertTrue(retrievedRequest.isPresent());
        assertEquals(travelRequestId, retrievedRequest.get().getTravelRequestId());

        verify(reimbursementRequestsRepository, times(1)).findByTravelRequestId(travelRequestId);
    }
}

