package com.sparta.schedulemanagementassignment.dto;

import com.sparta.schedulemanagementassignment.entity.Schedule;
import lombok.Getter;

import java.time.LocalDate;
@Getter
public class ScheduleResponseDto {
    private Long id;
    private String title;
    private String content;
    private String manager;
    private String password;
    private String date;

    public ScheduleResponseDto(Schedule schedule) {
        this.id = schedule.getId();
        this.title = schedule.getTitle();
        this.content = schedule.getContent();
        this.manager = schedule.getManager();
        this.password = schedule.getPassword();
        this.date = schedule.getDate();
    }
}
