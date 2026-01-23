package com.backend.v1.common.Converter;

import com.backend.v1.common.constant.Status;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class StatusConverter extends BaseEnumConverter<Status, Integer>{

    public StatusConverter() {
        super(Status.class);
    }
}