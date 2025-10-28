package com.project.Springframework.beans;

public enum CancellationReason {
	
    ITEM_OUT_OF_STOCK("Item out of stock"),
    DELIVERY_DELAY("Delivery delay"),
    CUSTOMER_REQUEST("Customer request"),
    OTHER("Other");

    private final String reason;

    CancellationReason(String reason) {
        this.reason = reason;
    }

    public String getReason() {
        return reason;
    }
}
