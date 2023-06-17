package com.employeetraveldesk.reimbursementmanagement.entity;

import java.sql.Date;
import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Entity
@Table(name = "reimbursementrequests")
public class ReimbursementRequests {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int TravelRequestId;
    private int RequestRaisedByEmployeeId;
    private LocalDate RequestDate;
    private int ReimbursementTypeId;
    private String InvoiceNo;
    private LocalDate InvoiceDate;
    private int InvoiceAmount;
    private String DocumentURL;
    private LocalDate RequestProcessedOn;
    private int RequestProcessedByEmployeeId;
    private String Remarks;
    private String Status;

    //generate fake json data for testing in camelcase
    /*
     * {
     * "travelRequestId": 1,
     * "requestRaisedByEmployeeId": 1,
     * "requestDate": "2021-07-01",
     * "reimbursementTypeId": 1,
     * "invoiceNo": "1",
     * "invoiceDate": "2021-07-01",
     * "invoiceAmount": 1,
     * "documentURL": "1",
     * "requestProcessedOn": "2021-07-01",
     * "requestProcessedByEmployeeId": 1,
     * "remarks": "Approved",
     * "status": "Approved"
     * }
     */
    //insert into reimbursementrequests (TravelRequestId, RequestRaisedByEmployeeId, RequestDate, ReimbursementTypeId, InvoiceNo, InvoiceDate, InvoiceAmount, DocumentURL, RequestProcessedOn, RequestProcessedByEmployeeId, Remarks, Status) values (1, 1, '2021-07-01', 1, '1', '2021-07-01', 1, '1', '2021-07-01', 1, 'Approved', 'Approved');
    //rewite this in underscore format
    //      
    
    @ManyToOne
    @JoinColumn(name = "reimbursementTypeId", insertable = false, updatable = false)
    private ReimbursementTypes reimbursementTypes;
}
