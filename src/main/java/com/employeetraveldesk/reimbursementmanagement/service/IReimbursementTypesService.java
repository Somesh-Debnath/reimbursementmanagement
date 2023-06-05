package com.employeetraveldesk.reimbursementmanagement.service;

import java.util.List;

import com.employeetraveldesk.reimbursementmanagement.entity.ReimbursementTypes;

public interface IReimbursementTypesService {
    //Return the available Reimbursement types
    public List<ReimbursementTypes> getReimbursementTypes();
}
