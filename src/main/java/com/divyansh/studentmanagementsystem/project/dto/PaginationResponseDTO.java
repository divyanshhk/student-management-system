package com.divyansh.studentmanagementsystem.project.dto;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;

import java.util.List;


public class PaginationResponseDTO<T> {

    private List<T> content;
    private int pageNumber;
    private int pageSize;
    private int totalElements;
    private int totalPages;
    private boolean last;

    public PaginationResponseDTO(Page<T> pageData) {
        this.content = pageData.getContent();
        this.pageNumber = pageData.getNumber();
        this.pageSize = pageData.getSize();
        this.totalElements = pageData.getNumberOfElements();
        this.totalPages = pageData.getTotalPages();
        this.last = pageData.isLast();
    }
}
