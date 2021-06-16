package com.gsu21se45.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AppointmentModel implements Serializable {

    private int id;
    private int conversationId;
    private int staffId;
    private Date scheduleDate;
    private Date createAt;
    private boolean status;
}
