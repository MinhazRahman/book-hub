package com.bookhub.dto;

import lombok.*;

@Data
@AllArgsConstructor
@Builder
public class PurchaseResponse {
    private  String orderTrackingNumber;
}
