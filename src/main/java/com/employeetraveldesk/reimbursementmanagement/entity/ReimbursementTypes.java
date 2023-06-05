package com.employeetraveldesk.reimbursementmanagement.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "reimbursementtypes")
public class ReimbursementTypes {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String Type;
    public char[] getReimbursementType() {
        return null;
    }
}
