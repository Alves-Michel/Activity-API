package com.example.activity_alura.domain.reservation;

public enum CancellationReason {
    CLIENT_REQUEST("CLIENT REQUEST"),
    SCHEDULE_CONFLICT("SCHEDULE CONFLICT"),
    ROOM_MAINTENANCE("ROOM MAINTENANCE"),
    EVENT_CANCELED("EVENT CANCELED"),
    DUPLICATE_BOOKING("DUPLICATE BOOKING"),
    NO_SHOW("NO-SHOW"),
    OTHER("OTHER REASON");

    private String reason;
    CancellationReason(String reason) {
        this.reason = reason;
    }
    public String getReason() {
        return reason;
    }

}
