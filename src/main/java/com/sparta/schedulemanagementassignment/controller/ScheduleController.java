package com.sparta.schedulemanagementassignment.controller;

import com.sparta.schedulemanagementassignment.dto.ScheduleRequestDto;
import com.sparta.schedulemanagementassignment.dto.ScheduleResponseDto;
import com.sparta.schedulemanagementassignment.entity.Schedule;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class ScheduleController {

    private final Map<Long, Schedule> scheduleList = new HashMap<>();

    @PostMapping("/schedules")
    public ScheduleResponseDto createMemo(@RequestBody ScheduleRequestDto requestDto) {
        Schedule schedule = new Schedule(requestDto);

        Long maxId = scheduleList.size() > 0 ? Collections.max(scheduleList.keySet()) + 1 : 1;
        schedule.setId(maxId);

        scheduleList.put(schedule.getId(), schedule);

        ScheduleResponseDto scheduleResponseDto = new ScheduleResponseDto(schedule);
        return scheduleResponseDto;

    }

    @GetMapping("/schedules")
    public List<ScheduleResponseDto> getSchedule() {
        List<ScheduleResponseDto> responseList = scheduleList.values().stream()
                .map(ScheduleResponseDto::new).toList();

        return responseList;
    }

}
