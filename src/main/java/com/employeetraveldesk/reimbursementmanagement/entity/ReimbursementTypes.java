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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String Type;

    //genereate sql insert statements for fake data
    /*
     * insert into reimbursementtypes (Type) values ('Food');
     * insert into reimbursementtypes (Type) values ('Water');
     * insert into reimbursementtypes (Type) values ('Laundry');
     * insert into reimbursementtypes (Type) values ('Local Travel');
     */
}
