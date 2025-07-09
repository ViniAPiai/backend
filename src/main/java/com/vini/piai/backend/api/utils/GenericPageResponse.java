package com.vini.piai.backend.api.utils;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class GenericPageResponse<T> {

    private Long totalItems;
    private Integer totalPages;
    private List<T> content;

}
