package com.divyansh.studentmanagementsystem.project.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.Page;

import java.util.List;

public class PaginationResponseDTO<T> {
    private List<T> content;
    private int pageNumber;
    private int pageSize;
    private long totalElements;
    private int totalPages;
    private boolean last;

    // Jackson needs this!
    public PaginationResponseDTO() {
    }

    public PaginationResponseDTO(Page<T> pageData) {
        this.content = pageData.getContent();
        this.pageNumber = pageData.getNumber();
        this.pageSize = pageData.getSize();
        this.totalElements = pageData.getTotalElements();
        this.totalPages = pageData.getTotalPages();
        this.last = pageData.isLast();
    }

    // --- MANUAL GETTERS (Jackson uses these to make the JSON) ---
    public List<T> getContent() { return content; }
    public int getPageNumber() { return pageNumber; }
    public int getPageSize() { return pageSize; }
    public long getTotalElements() { return totalElements; }
    public int getTotalPages() { return totalPages; }
    public boolean isLast() { return last; }

    // --- MANUAL SETTERS ---
    public void setContent(List<T> content) { this.content = content; }
    public void setPageNumber(int pageNumber) { this.pageNumber = pageNumber; }
    public void setPageSize(int pageSize) { this.pageSize = pageSize; }
    public void setTotalElements(long totalElements) { this.totalElements = totalElements; }
    public void setTotalPages(int totalPages) { this.totalPages = totalPages; }
    public void setLast(boolean last) { this.last = last; }
}
