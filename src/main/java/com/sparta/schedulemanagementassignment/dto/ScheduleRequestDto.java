package com.sparta.schedulemanagementassignment.dto;

import lombok.Getter;

import java.time.LocalDate;

@Getter
public class ScheduleRequestDto {
    private String title;
    private String content;
    private String manager;
    private String password;
    private String date;
}
