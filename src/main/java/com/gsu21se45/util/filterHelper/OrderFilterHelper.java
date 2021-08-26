package com.gsu21se45.util.filterHelper;

import org.springframework.data.domain.Sort;
import org.springframework.util.StringUtils;

import java.util.List;

public class OrderFilterHelper {
    private boolean isColumnValid = false;
    private final List<String> sortColumnsAllow;
    private final String sortColumn;
    private final Sort.Direction direction;

    public OrderFilterHelper(String order, List<String> sortColumnsAllow) {
        /*
         * Case: FE let ...&sort=&.... will get empty string
         * Let sort column to be empty to when validate will not match column validate
         */
        this.sortColumnsAllow = sortColumnsAllow;
        if (StringUtils.isEmpty(order)) {
            sortColumn = "r.view";
            direction = Sort.Direction.DESC;
            return;
        }
        char directionCharacter = order.charAt(0);

        if (directionCharacter == '-') {
            sortColumn = order.substring(1);
            direction = Sort.Direction.DESC;
        }
        else {
            sortColumn = order;
            direction = Sort.Direction.ASC;
        }
    }

    public Sort getSort() {
        return Sort.by(direction, sortColumn);
    }

    public void validate() {
        for (String currentCol : sortColumnsAllow) {
            if (currentCol.equals(sortColumn)) {
                isColumnValid = true;
                break;
            }
        }

//        if (!isColumnValid) {
//            throw new BadRequestException(sortColumn + " is not valid to be sorted. It should be " + sortColumnsAllow.toString());
//        }
    }
}
