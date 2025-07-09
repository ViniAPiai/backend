package com.vini.piai.backend.api.utils;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class GenericResponse<T> {

    private T data;
    private String message;

}
