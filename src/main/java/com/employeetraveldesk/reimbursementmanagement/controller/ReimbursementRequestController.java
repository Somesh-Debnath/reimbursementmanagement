package com.employeetraveldesk.reimbursementmanagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.employeetraveldesk.reimbursementmanagement.entity.ReimbursementRequests;
import com.employeetraveldesk.reimbursementmanagement.exception.ResourceNotFoundException;
import com.employeetraveldesk.reimbursementmanagement.repository.ReimbursementRequestsRepository;
import com.employeetraveldesk.reimbursementmanagement.service.IReimbursementRequestsService;
import com.employeetraveldesk.reimbursementmanagement.service.ReimbursementRequestsService;

@RequestMapping("/api")
@RestController
public class ReimbursementRequestController {
        @Autowired
        private IReimbursementRequestsService reimbursementRequestsService;
        @Autowired
        private ReimbursementRequestsRepository reimbursementRequestsRepository;

        @PostMapping(path = "/reimbursements/add", produces = { MediaType.APPLICATION_JSON_VALUE })
        public ResponseEntity<ReimbursementRequests> insertReimbursementRequest(
                        @RequestBody ReimbursementRequests reimbursementRequest) {
                ReimbursementRequests reimbursementRequests = reimbursementRequestsService
                                .insertReimbursementRequest(reimbursementRequest);
                return new ResponseEntity<ReimbursementRequests>(reimbursementRequests, HttpStatus.OK);
        }

        @GetMapping(path = "/reimbursements/{travelrequestid}/requests", produces = {
                        MediaType.APPLICATION_JSON_VALUE })
        ResponseEntity<ReimbursementRequests> findReimbursementRequestsByTravelRequestId(
                        @PathVariable int travelrequestid)
                        throws ResourceNotFoundException {
                ReimbursementRequests reimbursementRequests = reimbursementRequestsService
                                .getReimbursementRequestsByTravelRequestId(travelrequestid)
                                .orElseThrow(
                                                () -> new ResourceNotFoundException(
                                                                "ReimburementRequests not found for travel request id"));
                return new ResponseEntity<ReimbursementRequests>(reimbursementRequests, HttpStatus.OK);
        }

        @GetMapping(path = "/reimbursements/{reimbursementid}", produces = { MediaType.APPLICATION_JSON_VALUE })
        ResponseEntity<ReimbursementRequests> findReimbursementRequestById(@PathVariable int reimbursementid)
                        throws ResourceNotFoundException {
                ReimbursementRequests reimbursementRequests = reimbursementRequestsService
                                .getReimbursementRequestById(reimbursementid)
                                .orElseThrow(() -> new ResourceNotFoundException(
                                                "ReimbursementRequests not found for id"));
                return new ResponseEntity<ReimbursementRequests>(reimbursementRequests, HttpStatus.OK);
        }

        @PutMapping(path = "/reimbursements/{reimbursementid}/process", produces = { MediaType.APPLICATION_JSON_VALUE })

        ResponseEntity<ReimbursementRequests> processReimbursementRequest(@PathVariable(value = "reimbursementid") int id,
                        @RequestBody ReimbursementRequests reimbursementRequest) throws ResourceNotFoundException {
                ReimbursementRequests reimbursementRequests = reimbursementRequestsService
                                .processReimbursementRequest(reimbursementRequest, id);
                return new ResponseEntity<ReimbursementRequests>(reimbursementRequests, HttpStatus.OK);

        }
}