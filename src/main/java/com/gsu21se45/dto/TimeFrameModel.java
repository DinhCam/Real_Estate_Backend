package com.gsu21se45.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.sql.Time;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TimeFrameModel implements Serializable {

    private int id;
    private Time startTime;
    private Time endTime;
}
