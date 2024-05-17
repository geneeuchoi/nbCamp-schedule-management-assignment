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
@RequestMapping("/schedules")
public class ScheduleController {

    private final Map<Long, Schedule> scheduleList = new HashMap<>();

    //1단계 - 일정 post
    @PostMapping("/create")
    public ScheduleResponseDto createMemo(@RequestBody ScheduleRequestDto requestDto) {
        Schedule schedule = new Schedule(requestDto);

        Long maxId = scheduleList.size() > 0 ? Collections.max(scheduleList.keySet()) + 1 : 1;
        schedule.setId(maxId);

        scheduleList.put(schedule.getId(), schedule);

        ScheduleResponseDto scheduleResponseDto = new ScheduleResponseDto(schedule);
        return scheduleResponseDto;

    }

    //1단계 - 일정 조회
    @GetMapping("/read")
    public List<ScheduleResponseDto> getSchedule() {
        List<ScheduleResponseDto> responseList = scheduleList.values().stream()
                .map(ScheduleResponseDto::new).toList();

        return responseList;
    }

    //2단계 - 선택한 일정의 정보 조회
    @GetMapping("/read/{id}")
    public ScheduleResponseDto getScheduleById(@PathVariable Long id) {
        if(scheduleList.containsKey(id)) {
            //해당 메모 가져오기
            Schedule schedule = scheduleList.get(id);
            ScheduleResponseDto scheduleResponseDto = new ScheduleResponseDto(schedule);
            return scheduleResponseDto;
        } else {
            throw new IllegalArgumentException("선택한 스케쥴은 존재하지 않습니다.");
        }
    }

    //3단계 - 내림차순으로 일정 목록 조회
    @GetMapping("")
    public List<ScheduleResponseDto> getScheduleByReverseOrder() {
        List<ScheduleResponseDto> responseList = scheduleList.values().stream()
                .map(ScheduleResponseDto::new).toList();


        return responseList;
    }

    //4단계 - 선택한 일정 수정
    @PutMapping("/update")
    public ScheduleResponseDto updateSchedule(@RequestParam Long id, @RequestParam Long password, @RequestBody ScheduleRequestDto requestDto){
        if(scheduleList.containsKey(id)){
            Schedule schedule = scheduleList.get(id);
            if(schedule.getPassword().equals(password)){
                schedule.update(requestDto);
                ScheduleResponseDto scheduleResponseDto = new ScheduleResponseDto(schedule);
                return scheduleResponseDto;
            }
            else{
                throw new IllegalArgumentException("비밀번호가 다릅니다.");
            }
        }
        else{
            throw new IllegalArgumentException("존재하지 않는 일정입니다.");
        }
    }

    //5단계 - 선택한 일정 삭제
    @DeleteMapping("/delete")
    public void deleteMemo (@RequestParam Long id, @RequestParam Long password) {
        if(scheduleList.containsKey(id)){
            Schedule schedule = scheduleList.get(id);
            if(schedule.getPassword().equals(password)){
                scheduleList.remove(id);
            }
            else{
                throw new IllegalArgumentException("비밀번호가 다릅니다.");
            }
        }
        else{
            throw new IllegalArgumentException("존재하지 않는 일정입니다.");
        }
    }



}
