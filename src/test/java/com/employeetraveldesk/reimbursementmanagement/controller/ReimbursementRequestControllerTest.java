package com.employeetraveldesk.reimbursementmanagement.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.employeetraveldesk.reimbursementmanagement.dto.ProcessReimbursementDTO;
import com.employeetraveldesk.reimbursementmanagement.dto.ReimbursementDTO;
import com.employeetraveldesk.reimbursementmanagement.entity.ReimbursementRequests;
import com.employeetraveldesk.reimbursementmanagement.exception.ResourceNotFoundException;
import com.employeetraveldesk.reimbursementmanagement.repository.ReimbursementRequestsRepository;
import com.employeetraveldesk.reimbursementmanagement.service.IReimbursementRequestsService;

public class ReimbursementRequestControllerTest {

    @Mock
    private IReimbursementRequestsService reimbursementRequestsService;

    @Mock
    private ReimbursementRequestsRepository reimbursementRequestsRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private ReimbursementRequestController reimbursementRequestController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testInsertReimbursementRequest() {
        // Mock data
        ReimbursementRequests reimbursementRequest = new ReimbursementRequests();
        ReimbursementRequests savedRequest = new ReimbursementRequests();
        ReimbursementDTO expectedDTO = new ReimbursementDTO();

        when(modelMapper.map(reimbursementRequest, ReimbursementRequests.class)).thenReturn(reimbursementRequest);
        when(reimbursementRequestsService.insertReimbursementRequest(reimbursementRequest)).thenReturn(savedRequest);
        when(modelMapper.map(savedRequest, ReimbursementDTO.class)).thenReturn(expectedDTO);

        // Perform the test
        ResponseEntity<ReimbursementDTO> response = reimbursementRequestController
                .insertReimbursementRequest(reimbursementRequest);

        // Assertions
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedDTO, response.getBody());

        verify(modelMapper).map(reimbursementRequest, ReimbursementRequests.class);
        verify(reimbursementRequestsService).insertReimbursementRequest(reimbursementRequest);
        verify(modelMapper).map(savedRequest, ReimbursementDTO.class);
    }

    @Test
    public void testFindReimbursementRequestsByTravelRequestId() throws ResourceNotFoundException {
        // Mock data
        int travelRequestId = 1;
        ReimbursementRequests reimbursementRequests = new ReimbursementRequests();
        ReimbursementDTO expectedDTO = new ReimbursementDTO();

        when(reimbursementRequestsService.getReimbursementRequestsByTravelRequestId(travelRequestId))
                .thenReturn(Optional.of(reimbursementRequests));
        when(modelMapper.map(reimbursementRequests, ReimbursementDTO.class)).thenReturn(expectedDTO);

        // Perform the test
        ResponseEntity<ReimbursementDTO> response = reimbursementRequestController
                .findReimbursementRequestsByTravelRequestId(travelRequestId);

        // Assertions
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedDTO, response.getBody());

        verify(reimbursementRequestsService).getReimbursementRequestsByTravelRequestId(travelRequestId);
        verify(modelMapper).map(reimbursementRequests, ReimbursementDTO.class);
    }

    @Test
    public void testFindReimbursementRequestById() throws ResourceNotFoundException {
        // Mock data
        int reimbursementId = 1;
        ReimbursementRequests reimbursementRequests = new ReimbursementRequests();
        ReimbursementDTO expectedDTO = new ReimbursementDTO();

        when(reimbursementRequestsService.getReimbursementRequestById(reimbursementId))
                .thenReturn(Optional.of(reimbursementRequests));
        when(modelMapper.map(reimbursementRequests, ReimbursementDTO.class)).thenReturn(expectedDTO);

        // Perform the test
        ResponseEntity<ReimbursementDTO> response = reimbursementRequestController
                .findReimbursementRequestById(reimbursementId);

        // Assertions
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedDTO, response.getBody());

        verify(reimbursementRequestsService).getReimbursementRequestById(reimbursementId);
        verify(modelMapper).map(reimbursementRequests, ReimbursementDTO.class);
    }

    @Test
    public void testProcessReimbursementRequest() throws ResourceNotFoundException {
        // Mock data
        int reimbursementId = 1;
        ReimbursementRequests reimbursementRequest = new ReimbursementRequests();
        ReimbursementRequests processedRequest = new ReimbursementRequests();
        ProcessReimbursementDTO expectedDTO = new ProcessReimbursementDTO();

        when(reimbursementRequestsService.processReimbursementRequest(reimbursementRequest, reimbursementId))
                .thenReturn(processedRequest);
        when(modelMapper.map(processedRequest, ProcessReimbursementDTO.class)).thenReturn(expectedDTO);

        // Perform the test
        ResponseEntity<ProcessReimbursementDTO> response = reimbursementRequestController
                .processReimbursementRequest(reimbursementId, reimbursementRequest);

        // Assertions
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedDTO, response.getBody());

        verify(reimbursementRequestsService).processReimbursementRequest(reimbursementRequest, reimbursementId);
        verify(modelMapper).map(processedRequest, ProcessReimbursementDTO.class);
    }
}
