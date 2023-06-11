package com.employeetraveldesk.reimbursementmanagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.employeetraveldesk.reimbursementmanagement.dto.ProcessReimbursementDTO;
import com.employeetraveldesk.reimbursementmanagement.dto.ReimbursementDTO;
import com.employeetraveldesk.reimbursementmanagement.entity.ReimbursementRequests;
import com.employeetraveldesk.reimbursementmanagement.exception.ResourceNotFoundException;
import com.employeetraveldesk.reimbursementmanagement.repository.ReimbursementRequestsRepository;
import com.employeetraveldesk.reimbursementmanagement.service.IReimbursementRequestsService;
import com.employeetraveldesk.reimbursementmanagement.service.ReimbursementRequestsService;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RequestMapping("/api")
@RestController
public class ReimbursementRequestController {
        Logger logger = LoggerFactory.getLogger(ReimbursementRequestController.class);
        @Autowired
        private IReimbursementRequestsService reimbursementRequestsService;
        @Autowired
        private ReimbursementRequestsRepository reimbursementRequestsRepository;
        @Autowired
        private ModelMapper modelMapper;

        @PostMapping(path = "/reimbursements/add", produces = { MediaType.APPLICATION_JSON_VALUE })
        public ResponseEntity<ReimbursementDTO> insertReimbursementRequest(
                        @RequestBody ReimbursementRequests reimbursementRequest) {
                ReimbursementRequests requests = modelMapper.map(reimbursementRequest, ReimbursementRequests.class);
                ReimbursementRequests reimbursementRequests = reimbursementRequestsService
                                .insertReimbursementRequest(requests);
                ReimbursementDTO reimbursementDTO = modelMapper.map(reimbursementRequests, ReimbursementDTO.class);
                logger.info("Reimbursement Request added successfully");
                return new ResponseEntity<ReimbursementDTO>(reimbursementDTO, HttpStatus.OK);
        }

        @GetMapping(path = "/reimbursements/{travelrequestid}/requests", produces = {
                        MediaType.APPLICATION_JSON_VALUE })
        ResponseEntity<ReimbursementDTO> findReimbursementRequestsByTravelRequestId(
                        @PathVariable int travelrequestid)
                        throws ResourceNotFoundException {
                        
                ReimbursementRequests reimbursementRequests = reimbursementRequestsService
                                .getReimbursementRequestsByTravelRequestId(travelrequestid)
                                .orElseThrow(() -> new ResourceNotFoundException(
                                        "ReimburementRequests not found for travel request id"));
                ReimbursementDTO reimbursementDTO = modelMapper.map(reimbursementRequests, ReimbursementDTO.class);
                logger.info("Reimbursement Request for travel request found successfully");

                return new ResponseEntity<ReimbursementDTO>(reimbursementDTO, HttpStatus.OK);
        }

        @GetMapping(path = "/reimbursements/{reimbursementid}", produces = { MediaType.APPLICATION_JSON_VALUE })
        ResponseEntity<ReimbursementDTO> findReimbursementRequestById(@PathVariable int reimbursementid)
                        throws ResourceNotFoundException {
                ReimbursementRequests reimbursementRequests = reimbursementRequestsService
                                .getReimbursementRequestById(reimbursementid)
                                .orElseThrow(() -> new ResourceNotFoundException(
                                                "ReimbursementRequests not found for id"));
                ReimbursementDTO reimbursementDTO = modelMapper.map(reimbursementRequests, ReimbursementDTO.class);
                logger.info("Reimbursement Request found successfully");
                return new ResponseEntity<ReimbursementDTO>(reimbursementDTO, HttpStatus.OK);
        }

        @PutMapping(path = "/reimbursements/{reimbursementid}/process", produces = { MediaType.APPLICATION_JSON_VALUE })
        ResponseEntity<ProcessReimbursementDTO> processReimbursementRequest(
                        @PathVariable(value = "reimbursementid") int id,
                        @RequestBody ReimbursementRequests reimbursementRequest) throws ResourceNotFoundException {
                ReimbursementRequests reimbursementRequests = reimbursementRequestsService
                                .processReimbursementRequest(reimbursementRequest, id);
                ProcessReimbursementDTO processReimbursementDTO = modelMapper.map(reimbursementRequests,ProcessReimbursementDTO.class);
                logger.info("Reimbursement Request processed successfully");
                return new ResponseEntity<ProcessReimbursementDTO>(processReimbursementDTO, HttpStatus.OK);

        }
}