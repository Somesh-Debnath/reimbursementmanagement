package com.employeetraveldesk.reimbursementmanagement.entity;

import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "reimbursementrequests")
public class ReimbursementRequests {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    /*` id`, ` TravelRequestId`, ` RequestRaisedByEmployeeId`, ` RequestDate`, ` ReimbursementTypeId`, ` InvoiceNo`, ` InvoiceDate`, ` InvoiceAmount`, ` DocumentURL`, `RequestProcessedOn`, ` RequestProcessedByEmployeeId`, ` Remarks`, `Status` */
    private int id;
    private int TravelRequestId;
    private int RequestRaisedByEmployeeId;
    private Date RequestDate;
    private int ReimbursementTypeId;
    private String InvoiceNo;
    private Date InvoiceDate;
    private int InvoiceAmount;
    private String DocumentURL;
    private Date RequestProcessedOn;
    private int RequestProcessedByEmployeeId;
    private String Remarks;
    private String Status;
    
}
