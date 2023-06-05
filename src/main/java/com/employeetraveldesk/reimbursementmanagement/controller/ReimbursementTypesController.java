package com.employeetraveldesk.reimbursementmanagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.employeetraveldesk.reimbursementmanagement.entity.ReimbursementTypes;
import com.employeetraveldesk.reimbursementmanagement.exception.ResourceNotFoundException;
import com.employeetraveldesk.reimbursementmanagement.repository.ReimbursementTypesRepository;
import com.employeetraveldesk.reimbursementmanagement.service.IReimbursementTypesService;

@RequestMapping("/api")
@RestController
public class ReimbursementTypesController {
    @Autowired
    private IReimbursementTypesService reimbursementTypesService;
    @Autowired
    private ReimbursementTypesRepository reimbursementTypesRepository;

    // api/reimbursementtypes/  GET

    @GetMapping(path="/reimbursementtypes", produces={MediaType.APPLICATION_JSON_VALUE})
    //This endpoint will return details of all the reimbursement types
    public ResponseEntity<Iterable<ReimbursementTypes>> getAllReimbursementTypes() {
       List<ReimbursementTypes> reimbursementTypes = reimbursementTypesService.getReimbursementTypes();
       for(ReimbursementTypes reimbursementType : reimbursementTypes) {
           System.out.println(reimbursementType.getReimbursementType());
       }
         return new ResponseEntity<Iterable<ReimbursementTypes>>(reimbursementTypes, HttpStatus.OK);
    }

}
