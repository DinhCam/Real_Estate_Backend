package com.gsu21se45.util.filterHelper;

import org.springframework.data.domain.Pageable;

public class SortBuilder {
    public void buildOrder(StringBuilder builder, Pageable pageable) {
        builder.append(" ORDER BY ");
        pageable.getSort().stream().forEach(item -> {
            builder.append( item.getProperty())
                    .append(" ")
                    .append(item.getDirection())
                    .append(" ");
        });
    }
}
