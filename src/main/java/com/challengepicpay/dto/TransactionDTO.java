package com.challengepicpay.dto;

import java.math.BigDecimal;


//dto = data transactions objects

public record TransactionDTO(BigDecimal value, Long senderId, Long receiverId) {
}
