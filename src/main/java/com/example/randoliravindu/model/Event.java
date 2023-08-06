package com.example.randoliravindu.model;
import jakarta.persistence.*;
import lombok.Data;


import java.util.UUID;

@Entity
@Table(name = "events")
@Data
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID eventId;

    private UUID transId;
    private String transTms;
    private String rcNum;
    private String clientId;
    private int eventCnt;
    private String locationCd;
    private String locationId1;
    private String locationId2;
    private String addrNbr;



}