package com.job.designcodingtask.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderRequest {
    private String shipmentNumber;
    private String receiverEmail;
    private String receiverCountryCode;
    private String senderCountryCode;
    private int statusCode;
}
