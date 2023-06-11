package com.employeetraveldesk.reimbursementmanagement.controller;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.employeetraveldesk.reimbursementmanagement.dto.ReimbursementTypeDTO;
import com.employeetraveldesk.reimbursementmanagement.service.IReimbursementTypesService;

@RequestMapping("/api")
@RestController
public class ReimbursementTypesController {
  Logger logger = LoggerFactory.getLogger(ReimbursementTypesController.class);
  @Autowired
  private IReimbursementTypesService reimbursementTypesService;

  @Autowired
  private ModelMapper modelMapper;

  @GetMapping(path = "/reimbursementtypes", produces = { MediaType.APPLICATION_JSON_VALUE })
  public List<ReimbursementTypeDTO> getAllReimbursementTypes() {
    logger.info("ReimbursementTypes fetched successfully");
    return reimbursementTypesService.getReimbursementTypes().stream()
        .map(reimbursementType -> modelMapper.map(reimbursementType, ReimbursementTypeDTO.class))
        .toList();
  }
}
