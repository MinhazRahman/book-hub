package com.bookhub.dto;

import lombok.*;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
public class PurchaseResponse {
    private final String orderTrackingNumber;
}
