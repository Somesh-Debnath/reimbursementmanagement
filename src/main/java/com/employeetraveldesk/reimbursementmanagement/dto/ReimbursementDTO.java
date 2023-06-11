package com.employeetraveldesk.reimbursementmanagement.dto;

import java.time.LocalDate;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReimbursementDTO {
    private int id;
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
}
