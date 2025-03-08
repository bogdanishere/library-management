package com.example.spring_boot.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class MessageWithDataResponseDTO extends MessageResponseDTO {
    private Object data;

    public MessageWithDataResponseDTO(String message, Object data) {
        super(message);
        this.data = data;
    }
}
