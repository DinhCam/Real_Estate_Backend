package com.gsu21se45.common.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Page;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class PaginationResponse<T> {
    private final List<T> content;
    private final long totalRecord;
    private final long totalPage;
    private final int contentSize;
    private final int pageIndex;

    public PaginationResponse(Page<T> data) {
        this.content = data.getContent();
        this.contentSize = data.getContent().size();
        this.pageIndex = data.getPageable().getPageNumber();
        this.totalRecord = data.getTotalElements();
        this.totalPage = data.getTotalPages();
    }
}
