package com.example.randoliravindu.model;

import lombok.Data;

import java.util.List;
import java.util.UUID;
@Data
public class Payload {
    private UUID batchId;
    private List<Record> records;

    @Data
    public static class Record {
        private UUID transId;
        private String transTms;
        private String rcNum;
        private String clientId;
        private List<Event> event;
    }

    @Data
    public static class Event {
        private int eventCnt;
        private String locationCd;
        private String locationId1;
        private String locationId2;
        private String addrNbr;
    }

}
